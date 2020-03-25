package com.avans.AvansMovieApp.Utilities.JSONUtiliies;

import com.avans.AvansMovieApp.Model.DetailedMovie;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class getDetailedMovieFromMovieId implements HTTPRequestable {

    // to use this convetor, implement the interface MovieIdDetailedMovieConvertable
    // TODO: think about a better sollution. This is hard because of the asynchroness


    private DetailedMovie detailedMovie;
    private Integer movieId;

    public getDetailedMovieFromMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public void initializeMovieIdToDetailedMovieRequest() {
        MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(getDetailedMovieFromMovieId.this);
        makeReq.execute(String.format("https://api.themoviedb.org/3/movie/%d?api_key=b966d45d0ab662f523ce11044a9394ef&language=en-US", this.movieId));
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
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
