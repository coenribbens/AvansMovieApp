package com.avans.AvansMovieApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.avans.AvansMovieApp.Adapters.ListRecycleViewAdapter;
import com.avans.AvansMovieApp.Model.ListMovie;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.MovieList;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        this.recyclerView = findViewById(R.id.rv_list_items);
        final String listId = getIntent().getStringExtra("listId");
        final String userId = getIntent().getStringExtra("userId");
        MovieList movieList = new MovieList(userId, listId);
        movieList.initialiseGetListRequest();


    }

    //TODO change the name of this method to the actual callback method name.
    public void callBack(ArrayList<ListMovie> listMovies){
        ListRecycleViewAdapter listRecycleViewAdapter = new ListRecycleViewAdapter(listMovies, this);
        this.recyclerView.setAdapter(listRecycleViewAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
