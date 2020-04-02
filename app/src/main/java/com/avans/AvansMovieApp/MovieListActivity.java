package com.avans.AvansMovieApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.avans.AvansMovieApp.Adapters.MovieListRecycleViewAdapter;
import com.avans.AvansMovieApp.Adapters.MovieRecycleViewAdapter;
import com.avans.AvansMovieApp.Model.CompactMovie;
import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.GetListDetails;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.ListDetailsConvertable;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.RemoveMovieList;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity implements ListDetailsConvertable {
    private String listId;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listId = getIntent().getStringExtra("listId");
//        setTitle();
        this.recyclerView = findViewById(R.id.rv_list_items);
        GetListDetails getListDetails = new GetListDetails(this);
        getListDetails.initialiseCreateMovieList(listId);
    }

    @Override
    public void processListDetailsConvertableResult(ArrayList<CompactMovie> cm) {
        MovieListRecycleViewAdapter movieRecycleViewAdapter = new MovieListRecycleViewAdapter(cm, this, getIntent().getStringExtra("listId"));
        this.recyclerView.setAdapter(movieRecycleViewAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.remove_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_remove_list:
                RemoveMovieList removeMovieList = new RemoveMovieList();
                removeMovieList.initialiseCreateMovieList(GlobalVariables.SESSION_TOKEN, this.listId);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
