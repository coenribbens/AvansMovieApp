package com.avans.AvansMovieApp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avans.AvansMovieApp.Model.CompactMovie;
import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.MovieDetailActivity;
import com.avans.AvansMovieApp.MovieListActivity;
import com.avans.AvansMovieApp.R;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.RemoveMovieFromList;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieListRecycleViewAdapter extends RecyclerView.Adapter<MovieListRecycleViewAdapter.MovieViewHolder> {
    private static final String TAG = "RecycleViewAdapter";

    private ArrayList<CompactMovie> movies;
    private Context context;
    private String listId;
    private MovieListActivity concreteContext;

    public MovieListRecycleViewAdapter(ArrayList<CompactMovie> movies, Context context, String listId){
        this.movies = movies;
        this.context = context;
        this.listId = listId;
        this.concreteContext = (MovieListActivity)context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, int position) {
        if(!movies.get(position).getPosterPath().equals("null")){
            Glide.with(this.context)
                    .asBitmap()
                    .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movies.get(position).getPosterPath())
                    .into(holder.image);
        }
        else{
            holder.image.setImageResource(R.drawable.ic_broken_image_black_24dp);
        }
        holder.movieTitle.setText(movies.get(position).getTitle());

        //Get all the genre names from the List and concatenate these together.
        holder.year.setText("(" + movies.get(position).getReleaseDate().split("-")[0] + ")");
        holder.score.setText(movies.get(position).getVoteAverage() + "");
        holder.language.setText(movies.get(position).getOriginalLanguage());

        holder.context = this.context;
        holder.movie = movies.get(position);


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
        TextView year;
        TextView score;
        TextView language;
        Context context;
        CompactMovie movie;
        ImageButton deleteButton;

        public MovieViewHolder(@NonNull final View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_movie_item_image);
            movieTitle = itemView.findViewById(R.id.tv_movie_item_title);
            year = itemView.findViewById(R.id.tv_movie_item_year);
            score = itemView.findViewById(R.id.tv_movie_item_score);
            language = itemView.findViewById(R.id.tv_movie_item_language);
            deleteButton = itemView.findViewById(R.id.ib_movie_item_remove);

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RemoveMovieFromList removeMovieFromList = new RemoveMovieFromList();
                    removeMovieFromList.initialiseCreateMovieList(GlobalVariables.SESSION_TOKEN, listId, movie.getId() + "");
                    concreteContext.recreate();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MovieDetailActivity.class);
                    intent.putExtra("movieid", movie.getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
