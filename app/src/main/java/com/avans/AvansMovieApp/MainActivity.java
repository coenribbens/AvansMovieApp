package com.avans.AvansMovieApp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.avans.AvansMovieApp.Model.SmallMovie;
import com.avans.AvansMovieApp.Model.GlobalSettings;
import com.avans.AvansMovieApp.Utilities.JSONUtiliies.CreateNewSession;
import com.avans.AvansMovieApp.Utilities.JSONUtiliies.ParseJSONPopularToMovies;
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


    // TODO: save session id on rotate,lifecyclevent


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.recyclerView = findViewById(R.id.rv_movie_items);

        //


        MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(MainActivity.this);
        String baseUrl = "";
        makeReq.execute(
                String.format("https://api.themoviedb.org/3/movie/popular?api_key=%s&language=%s&page=1", GlobalSettings.API_KEY_V3, GlobalSettings.LANG)
        );


        //!!!! TODO RM
        CreateNewSession createNewSession = new CreateNewSession();
        createNewSession.initializeCreateNewSessionRequest();
        Log.v("{{SESS}}",""+GlobalSettings.SESSION_TOKEN);
    }


    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {

        ParseJSONPopularToMovies parser = new ParseJSONPopularToMovies(HTTPGETResponse);

        try{
            parser.fetchSmallMovies();
            ArrayList<SmallMovie> movies = parser.getSmallMovies();
            MovieRecycleViewAdapter movieRecycleViewAdapter = new MovieRecycleViewAdapter(movies, this);
            this.recyclerView.setAdapter(movieRecycleViewAdapter);
            this.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }




}


