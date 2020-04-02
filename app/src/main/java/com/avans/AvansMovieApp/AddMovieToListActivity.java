package com.avans.AvansMovieApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.avans.AvansMovieApp.Adapters.AddToListRecycleViewAdapter;
import com.avans.AvansMovieApp.Adapters.ListRecycleViewAdapter;
import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Model.ListMovie;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.MovieList;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.MovieListsConvertable;

import java.util.ArrayList;

public class AddMovieToListActivity extends AppCompatActivity implements MovieListsConvertable {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle("Add movie to list");
        this.recyclerView = findViewById(R.id.rv_list_items);
        MovieList movieList = new MovieList(GlobalVariables.SESSION_TOKEN, this);
//        movieList.initialiseGetListRequest();


    }

    @Override
    public void processMovieListConvertableResult(ArrayList<ListMovie> lm) {
        AddToListRecycleViewAdapter listRecycleViewAdapter = new AddToListRecycleViewAdapter(lm, this, getIntent().getIntExtra("movieId", 0));;
        this.recyclerView.setAdapter(listRecycleViewAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
