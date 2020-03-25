package com.avans.AvansMovieApp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.avans.AvansMovieApp.R;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPPOSTRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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

        //MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(MainActivity.this);
        //makeReq.execute("https://api.themoviedb.org/3/authentication/guest_session/new?api_key=b966d45d0ab662f523ce11044a9394ef\n");
        MakeHTTPPOSTRequest makeReq = new MakeHTTPPOSTRequest(MainActivity.this);
        makeReq.execute(
                "https://webhook.site/916a3a21-8a2e-4ba2-a3bf-cdfc8c620496",
                "{  'access_token': 'eyK0eXAiOiJAV1QiLCJhbGciOiUIUzI1NiJ9.eyJhdWQiOiI0Ozc2YzA1ZTg4YTY1Yzk0MjFjZDI1NmBiYzRiNGE0NyIsInN1YiI6IjRiYzg4OTJhMDE3YTNjMGY5MjAwMDAwMiIsInNjb3BlayI6WyJhcGlfcmVhZCJdLCL2ZXJzaW9uIjoxfQ.Bn660W0Vi-_AI5HvwIEqtc2s5mAXDknBnTrUREZYH7A'}"
                );


    }


    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {
        Log.v("{{REQUEST-1}}",HTTPGETResponse);
        // process the body e.g. by parsing it
    }
}


