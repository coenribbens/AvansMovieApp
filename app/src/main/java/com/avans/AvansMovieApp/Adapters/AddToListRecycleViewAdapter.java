package com.avans.AvansMovieApp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avans.AvansMovieApp.AddMovieToListActivity;
import com.avans.AvansMovieApp.Model.DetailedMovie;
import com.avans.AvansMovieApp.Model.ListMovie;
import com.avans.AvansMovieApp.MovieListActivity;
import com.avans.AvansMovieApp.R;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.AddMovieToList;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AddToListRecycleViewAdapter extends RecyclerView.Adapter<AddToListRecycleViewAdapter.MovieViewHolder> {
    private static final String TAG = "RecycleViewAdapter";

    private ArrayList<ListMovie> listsMovies;
    private Context context;
    private int movieId;

    public AddToListRecycleViewAdapter(ArrayList<ListMovie> listsMovies, Context context, int movieId){
        this.listsMovies = listsMovies;
        this.context = context;
        this.movieId = movieId;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
        if(!listsMovies.get(position).getPosterPath().equals("null")){
            Glide.with(this.context)
                    .asBitmap()
                    .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + listsMovies.get(position).getPosterPath())
                    .into(holder.image);
        }
        else{
            holder.image.setImageResource(R.drawable.ic_broken_image_black_24dp);
        }
        holder.listName.setText(listsMovies.get(position).getListName());
        holder.description.setText(listsMovies.get(position).getDescription());
        holder.language.setText(listsMovies.get(position).getLanguage());

        holder.context = this.context;
        holder.listMovie = listsMovies.get(position);
        holder.movieId = this.movieId;


    }

    @Override
    public int getItemCount() {
        if(this.listsMovies == null){
            return 0;
        }
        return this.listsMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView listName;
        TextView language;
        TextView description;
        Context context;
        ListMovie listMovie;
        int movieId;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_list_item_image);
            listName = itemView.findViewById(R.id.tv_list_item_title);
            language = itemView.findViewById(R.id.tv_list_item_language);
            description = itemView.findViewById(R.id.tv_list_item_description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddMovieToList addMovieToList = new AddMovieToList();

                    Toast.makeText(context, "Movie has been added", Toast.LENGTH_LONG);
                }
            });

        }
    }
}
