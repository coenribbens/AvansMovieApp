package com.avans.AvansMovieApp.Utilities.JSONUtiliies;

import com.avans.AvansMovieApp.Model.DetailedMovie;
import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class GetDetailedMovieFromMovieId implements HTTPRequestable {


    private DetailedMovie detailedMovie;
    private Integer movieId;
    private MovieIdDetailedMovieConvertable context;

    private String API_ENDPOINT = "/movie/";
    private String HTTP_GET_PARAMETERS = String.format("?api_key=%s&language=%s", GlobalVariables.API_KEY_V3, GlobalVariables.LANG);

    public GetDetailedMovieFromMovieId(Integer movieId, MovieIdDetailedMovieConvertable context) {
        this.movieId = movieId;
        this.context = context;
    }

    public void initializeMovieIdToDetailedMovieRequest() {
        MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(GetDetailedMovieFromMovieId.this);
        makeReq.execute(GlobalVariables.V3_BASE_URL + API_ENDPOINT + + this.movieId + HTTP_GET_PARAMETERS);
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
