package com.avans.AvansMovieApp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.avans.AvansMovieApp.Model.DetailedMovie;
import com.avans.AvansMovieApp.Utilities.JSONUtiliies.MovieIdDetailedMovieConvertable;
import com.avans.AvansMovieApp.Utilities.JSONUtiliies.ParseJSONPopularToMovies;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

import org.json.JSONException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements HTTPRequestable {
    private EditText searchBar;
    private Button searchButton;
    private RecyclerView recyclerView;
    private final String apiKey = "b966d45d0ab662f523ce11044a9394ef"; // maybe putting the api key in plaintext in here is a bad idea




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //

        MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(MainActivity.this);
        makeReq.execute("https://api.themoviedb.org/3/movie/popular?api_key=b966d45d0ab662f523ce11044a9394ef&language=en-US&page=1");




    }


    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {

        Log.v("{{REQUEST-1}}",HTTPGETResponse);
        // process the body e.g. by parsing it
        ParseJSONPopularToMovies parser = new ParseJSONPopularToMovies(HTTPGETResponse);

        //parser.fetchSmallMovies();
        //Log.v("{{PARSING}}",parser.getSmallMovies().toString());
        //parser.initializeMovieIdToDetailedMovie(100);

        //Log.v("{{PARSEE-1}}",            parser.getMovieIdToDetailedMovie().toString());


    }


}


