package com.avans.AvansMovieApp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avans.AvansMovieApp.Model.ListMovie;
import com.avans.AvansMovieApp.MovieDetailActivity;
import com.avans.AvansMovieApp.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListRecycleViewAdapter extends RecyclerView.Adapter<ListRecycleViewAdapter.MovieViewHolder> {
    private static final String TAG = "RecycleViewAdapter";

    private ArrayList<ListMovie> listsMovies;
    private Context context;

    public ListRecycleViewAdapter(ArrayList<ListMovie> listsMovies, Context context){
        this.listsMovies = listsMovies;
        this.context = context;
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

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_list_item_image);
            listName = itemView.findViewById(R.id.tv_list_item_title);
            language = itemView.findViewById(R.id.tv_list_item_language);
            description = itemView.findViewById(R.id.tv_list_item_description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MovieDetailActivity.class);
                    intent.putExtra("listid", listMovie.getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
