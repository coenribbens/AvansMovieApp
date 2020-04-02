package com.avans.AvansMovieApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.avans.AvansMovieApp.Adapters.MovieRecycleViewAdapter;
import com.avans.AvansMovieApp.Model.CompactMovie;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.GetListDetails;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
//        setTitle();
        this.recyclerView = findViewById(R.id.rv_list_items);
        GetListDetails getListDetails = new GetListDetails();
        getListDetails.initialiseCreateMovieList(getIntent().getStringExtra("listId"));
    }

    //TODO Callback method
    public void callBack(ArrayList<CompactMovie> compactMovies){
        MovieRecycleViewAdapter movieRecycleViewAdapter = new MovieRecycleViewAdapter(compactMovies, this);
        this.recyclerView.setAdapter(movieRecycleViewAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
