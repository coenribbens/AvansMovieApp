package com.avans.AvansMovieApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.avans.AvansMovieApp.Adapters.ListRecycleViewAdapter;
import com.avans.AvansMovieApp.Model.GlobalVariables;
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
        setTitle("My Lists");
        this.recyclerView = findViewById(R.id.rv_list_items);
        MovieList movieList = new MovieList(GlobalVariables.getGuestSessionID());
        movieList.initialiseGetListRequest();


    }

    //TODO change the name of this method to the actual callback method name.
    public void callBack(ArrayList<ListMovie> listMovies){
        ListRecycleViewAdapter listRecycleViewAdapter = new ListRecycleViewAdapter(listMovies, this);
        this.recyclerView.setAdapter(listRecycleViewAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
