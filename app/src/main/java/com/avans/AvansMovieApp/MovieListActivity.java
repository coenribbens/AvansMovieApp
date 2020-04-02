package com.avans.AvansMovieApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.avans.AvansMovieApp.Adapters.MovieListRecycleViewAdapter;
import com.avans.AvansMovieApp.Adapters.MovieRecycleViewAdapter;
import com.avans.AvansMovieApp.Model.CompactMovie;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.GetListDetails;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.ListDetailsConvertable;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity implements ListDetailsConvertable {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
//        setTitle();
        this.recyclerView = findViewById(R.id.rv_list_items);
        GetListDetails getListDetails = new GetListDetails(this);
        getListDetails.initialiseCreateMovieList(getIntent().getStringExtra("listId"));
    }

    @Override
    public void processListDetailsConvertableResult(ArrayList<CompactMovie> cm) {
        MovieListRecycleViewAdapter movieRecycleViewAdapter = new MovieListRecycleViewAdapter(cm, this);
        this.recyclerView.setAdapter(movieRecycleViewAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
