package com.avans.AvansMovieApp;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.avans.AvansMovieApp.Adapters.MovieRecycleViewAdapter;
import com.avans.AvansMovieApp.Model.CompactMovie;
import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.CreateNewSession;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.GetPopularMovies;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.GetSearchedMovies;
import com.avans.AvansMovieApp.Utilities.JSONUtiliies.ParseJSONPopularToCompactMovie;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements HTTPRequestable {
    private EditText searchBar;
    private Button searchButton;
    private RecyclerView recyclerView;
    private Integer page = 1;


    // TODO: save session id on rotate,lifecyclevent


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.recyclerView = findViewById(R.id.rv_movie_items);

        setTitle(getResources().getText(R.string.pop_main_ac_title));

        GetPopularMovies getPopularMovies = new GetPopularMovies(this);
        getPopularMovies.getPopularMovies();


        // search
        this.searchButton = findViewById(R.id.btn_search_button);
        this.searchBar = findViewById(R.id.search_bar);
        searchBar.setInputType(InputType.TYPE_CLASS_TEXT);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GetSearchedMovies getSearchedMovies = new GetSearchedMovies(
                        (HTTPRequestable) MainActivity.this,
                        MainActivity.this.searchBar.getText().toString()
                        );
                getSearchedMovies.getSearchedMovies();
            }
        });



        //!!!! TODO work on sessions
        CreateNewSession createNewSession = new CreateNewSession();
        createNewSession.initializeCreateNewSessionRequest();
        Log.v("{{SESS}}",""+ GlobalVariables.SESSION_TOKEN);
    }


    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {

        //Log.v("resp",HTTPGETResponse);
        ParseJSONPopularToCompactMovie parser = new ParseJSONPopularToCompactMovie(HTTPGETResponse);

        try{
            parser.fetchSmallMovies();
            ArrayList<CompactMovie> movies = parser.getCompactMovies();
            MovieRecycleViewAdapter movieRecycleViewAdapter = new MovieRecycleViewAdapter(movies, this);
            this.recyclerView.setAdapter(movieRecycleViewAdapter);
            this.recyclerView.setLayoutManager(new LinearLayoutManager(this));

           // TODO reintroduce hot loading functionality

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }




}


