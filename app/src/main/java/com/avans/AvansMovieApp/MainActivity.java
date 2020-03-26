package com.avans.AvansMovieApp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.avans.AvansMovieApp.Model.CompactMovie;
import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.CreateNewSession;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.GetPopularMovies;
import com.avans.AvansMovieApp.Utilities.JSONUtiliies.ParseJSONPopularToCompactMovie;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
        getPopularMovies.getPopularMoves();


        //!!!! TODO work on sessions
        CreateNewSession createNewSession = new CreateNewSession();
        createNewSession.initializeCreateNewSessionRequest();
        Log.v("{{SESS}}",""+ GlobalVariables.SESSION_TOKEN);
    }


    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {

        ParseJSONPopularToCompactMovie parser = new ParseJSONPopularToCompactMovie(HTTPGETResponse);

        try{
            parser.fetchSmallMovies();
            ArrayList<CompactMovie> movies = parser.getCompactMovies();
            MovieRecycleViewAdapter movieRecycleViewAdapter = new MovieRecycleViewAdapter(movies, this);
            this.recyclerView.setAdapter(movieRecycleViewAdapter);
            this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }




}


