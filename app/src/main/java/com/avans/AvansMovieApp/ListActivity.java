package com.avans.AvansMovieApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.avans.AvansMovieApp.Adapters.ListRecycleViewAdapter;
import com.avans.AvansMovieApp.Datalayer.MovieDBHandler;
import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Model.ListMovie;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.MovieList;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.MovieListsConvertable;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements MovieListsConvertable {
    private RecyclerView recyclerView;
    private String TAG = this.getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MovieDBHandler mdh = new MovieDBHandler(getApplicationContext(), "userList.db", null, +1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle("My Lists");
        this.recyclerView = findViewById(R.id.rv_list_items);
        MovieList movieList = new MovieList(GlobalVariables.SESSION_TOKEN, this);
        //movieList.initialiseGetListRequest();


    }

    @Override
    public void processMovieListConvertableResult(ArrayList<ListMovie> lm) {
        ListRecycleViewAdapter listRecycleViewAdapter = new ListRecycleViewAdapter(lm, this);
        this.recyclerView.setAdapter(listRecycleViewAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
