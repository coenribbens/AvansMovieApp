package com.avans.AvansMovieApp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.avans.AvansMovieApp.R;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPGETRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements HTTPGETRequestable {
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
        makeReq.execute("https://api.themoviedb.org/3/authentication/guest_session/new?api_key=b966d45d0ab662f523ce11044a9394ef\n");


    }


    @Override
    public void ProcessHTTPGETResponse(String HTTPGETResponse) {
        Log.v("[REQUEST-1]",HTTPGETResponse);
    }
}


