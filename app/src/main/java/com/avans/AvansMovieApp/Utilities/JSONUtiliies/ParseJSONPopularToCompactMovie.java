package com.avans.AvansMovieApp.Utilities.JSONUtiliies;

import com.avans.AvansMovieApp.Model.CompactMovie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParseJSONPopularToCompactMovie {

    private ArrayList<CompactMovie> compactMovies = new ArrayList<CompactMovie>();
    private String rawResponseBody;

    public ParseJSONPopularToCompactMovie(String rawResponseBody){
        this.rawResponseBody = rawResponseBody;
    }

    public void fetchSmallMovies() throws JSONException {
        JSONObject parentObject =  new JSONObject(this.rawResponseBody);
        JSONArray  moviesArr = parentObject.getJSONArray("results");

        for (int i = 0; i < moviesArr.length(); ++i) {
            final JSONObject movieJSONObj = moviesArr.getJSONObject(i);

            // coverting genreIds to a real arraylist
            JSONArray JSONgenreIds = movieJSONObj.getJSONArray("genre_ids");
            ArrayList<Integer> genreIds = new ArrayList<Integer>();

            for (int ii = 0; ii < JSONgenreIds.length(); ++ii) {
                genreIds.add(JSONgenreIds.getInt(ii));
            }

            compactMovies.add(
                    new CompactMovie(
                            movieJSONObj.getInt("popularity"),
                            movieJSONObj.getInt("vote_count"),
                            movieJSONObj.getBoolean("video"),
                            movieJSONObj.getString("poster_path"),
                            movieJSONObj.getInt("id"),
                            movieJSONObj.getBoolean("adult"),
                            movieJSONObj.getString("backdrop_path"),
                            movieJSONObj.getString("original_language"),
                            movieJSONObj.getString("original_title"),
                            genreIds,
                            movieJSONObj.getString("title"),
                            movieJSONObj.getInt("vote_average"),
                            movieJSONObj.getString("overview"),
                            movieJSONObj.getString("release_date")
                    )
            );
        }

    }

    public ArrayList<CompactMovie> getCompactMovies() {
        return compactMovies;
    }



}
