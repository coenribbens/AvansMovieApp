package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import com.avans.AvansMovieApp.Model.DetailedMovie;
import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
        System.out.println(HTTPGETResponse);

        try {
            JSONObject JSONdetailedMovie = new JSONObject(HTTPGETResponse);

            //genreNames
            ArrayList<String> genreNames = new ArrayList<>();
            JSONArray JSONgenres = JSONdetailedMovie.getJSONArray("genres");
            for(int x = 0;x < JSONgenres.length();x++){
                genreNames.add(JSONgenres.getJSONObject(x).getString("name"));
            }

            //productionCompaniesNames
            ArrayList<String> companyNames = new ArrayList<>();
            JSONArray JSONcompanies = JSONdetailedMovie.getJSONArray("production_companies");
            for(int x = 0;x < JSONcompanies.length();x++){
                companyNames.add(JSONcompanies.getJSONObject(x).getString("name"));
            }


            this.detailedMovie = new DetailedMovie(
                    JSONdetailedMovie.getString("original_title"),
                    JSONdetailedMovie.getString("title"),
                    genreNames, // genreNames
                    JSONdetailedMovie.getString("homepage"),
                    JSONdetailedMovie.getString("original_language"),
                    JSONdetailedMovie.getInt("popularity"),
                    JSONdetailedMovie.getString("poster_path"),
                    companyNames, // production companies
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
