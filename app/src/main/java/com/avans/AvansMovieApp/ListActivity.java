package com.avans.AvansMovieApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
        setTitle(getString(R.string.my_lists));
        this.recyclerView = findViewById(R.id.rv_list_items);
        MovieList movieList = new MovieList(GlobalVariables.SESSION_TOKEN, this);
        movieList.initialiseGetListRequest();


    }

    @Override
    public void processMovieListConvertableResult(ArrayList<ListMovie> lm) {
        ListRecycleViewAdapter listRecycleViewAdapter = new ListRecycleViewAdapter(lm, this);
        this.recyclerView.setAdapter(listRecycleViewAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lists_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_create_list:
                Intent intent = new Intent(this, CreateListActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
