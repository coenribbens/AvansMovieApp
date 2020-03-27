package com.avans.AvansMovieApp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.avans.AvansMovieApp.Model.Review;
import com.avans.AvansMovieApp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReviewAdapter extends ArrayAdapter<Review> {

    private Context mContext;
    private int resourceLayout;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        Review p = getItem(position);

        if (p != null) {
            TextView Author = (TextView) v.findViewById(R.id.tv_review_item_author);
            TextView Content  = (TextView) v.findViewById(R.id.tv_review_item_content);


            if (Author != null) {
                Author.setText(p.getAuthor());
            }

            if (Content != null) {
                Content.setText(p.getContent());
            }


        }

        return v;
    }




    public ReviewAdapter(Context context, int resourceLayout, ArrayList<Review> objecten){

        super(context, resourceLayout,objecten);

        mContext = context;
        this.resourceLayout = resourceLayout;

    }
}
