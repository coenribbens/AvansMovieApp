package com.avans.AvansMovieApp.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.avans.AvansMovieApp.Model.Review;

import java.util.ArrayList;

public class ReviewAdapter extends ArrayAdapter<Review> {

    private Context mContext;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    public ReviewAdapter(Context context, int resource, ArrayList<Review> objecten){
        super(context, resource,objecten);

        mContext = context;

    }
}
