package com.avans.AvansMovieApp.Utilities.JSONUtiliies;


import com.avans.AvansMovieApp.Model.CompactMovie;
import com.avans.AvansMovieApp.Model.Review;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParseJSONReviews {
    private ArrayList<Review> Reviews = new ArrayList<Review>();
    private String rawResponseBody;

    public ParseJSONReviews(String rawResponseBody){
        this.rawResponseBody = rawResponseBody;
    }
    public void fetchReviews() throws JSONException {
        JSONObject parentObject =  new JSONObject(this.rawResponseBody);
        JSONArray  ReviewArray = parentObject.getJSONArray("results");

        for (int i = 0; i < ReviewArray.length(); ++i) {
            final JSONObject reviewJSONObj = ReviewArray.getJSONObject(i);



            Reviews.add(
                    new Review(
                            reviewJSONObj.getString("author"),
                            reviewJSONObj.getString("content")));



        }
}}
