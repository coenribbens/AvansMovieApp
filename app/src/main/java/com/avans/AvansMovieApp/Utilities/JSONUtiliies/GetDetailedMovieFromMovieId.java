package com.avans.AvansMovieApp.Utilities.JSONUtiliies;

import com.avans.AvansMovieApp.Model.DetailedMovie;
import com.avans.AvansMovieApp.Model.GlobalSettings;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class GetDetailedMovieFromMovieId implements HTTPRequestable {


    private DetailedMovie detailedMovie;
    private Integer movieId;
    private MovieIdDetailedMovieConvertable context;

    public GetDetailedMovieFromMovieId(Integer movieId, MovieIdDetailedMovieConvertable context) {
        this.movieId = movieId;
        this.context = context;
    }

    public void initializeMovieIdToDetailedMovieRequest() {
        MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(GetDetailedMovieFromMovieId.this);
        makeReq.execute(String.format("https://api.themoviedb.org/3/movie/%d?api_key=%s&language=%s", this.movieId, GlobalSettings.apiKey, GlobalSettings.language));
    }
    @Override


    public void ProcessHTTPResponseBody(String HTTPGETResponse) {

        try {
            JSONObject JSONdetailedMovie = new JSONObject(HTTPGETResponse);
            this.detailedMovie = new DetailedMovie(
                    JSONdetailedMovie.getString("original_title"),
                    JSONdetailedMovie.getString("title"),
                    null, // genreNames
                    JSONdetailedMovie.getString("homepage"),
                    JSONdetailedMovie.getString("original_language"),
                    JSONdetailedMovie.getInt("popularity"),
                    JSONdetailedMovie.getString("poster_path"),
                    null, // production companies
                    JSONdetailedMovie.getString("release_date"),
                    JSONdetailedMovie.getInt("runtime"),
                    JSONdetailedMovie.getString("tagline"),
                    JSONdetailedMovie.getString("overview")

            );
            context.processMovieIdDetailedMovieConversionResult(this.detailedMovie);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
