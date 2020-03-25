package com.avans.AvansMovieApp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avans.AvansMovieApp.Model.Movie;
import com.avans.avans_movie_app.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieRecycleViewAdapter extends RecyclerView.Adapter<MovieRecycleViewAdapter.MovieViewHolder> {
    private static final String TAG = "RecycleViewAdapter";

    private ArrayList<Movie> movies;
    private Context context;

    public MovieRecycleViewAdapter(ArrayList<Movie> movies, Context context){
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
        Glide.with(this.context)
                .asBitmap()
                .load(movies.get(position).getPosterPath())
                .into(holder.image);
        holder.movieTitle.setText(movies.get(position).getTitle());

        //Get all the genre names from the List and concatenate these together.
        StringBuilder genres = new StringBuilder();
        for(String genre : movies.get(position).getGenreNames()){
            if(genres.length() == 0){
                genres.append(genre);
            }
            else {
                genres.append(", ").append(genre);
            }
        }
        holder.genres.setText(genres);
        holder.context = this.context;


    }

    @Override
    public int getItemCount() {
        if(this.movies == null){
            return 0;
        }
        return this.movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView movieTitle;
        TextView genres;
        Context context;
        Movie movie;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_movie_item_image);
            movieTitle = itemView.findViewById(R.id.tv_movie_item_title);
            genres = itemView.findViewById(R.id.tv_movie_item_genre);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MovieDetailActivity.class);
                    intent.putExtra("movie", MovieViewHolder.this.movie);
                }
            });

        }
    }
}
