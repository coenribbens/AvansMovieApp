package com.avans.AvansMovieApp.Utilities.JSONUtiliies;

import com.avans.AvansMovieApp.Model.DetailedMovie;
import com.avans.AvansMovieApp.Model.GlobalSettings;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetYoutubeIdFromMovieId implements HTTPRequestable {

    private Integer movieId;
    private String youtubeId;
    private MovieIdYoutubeIdConvertable context;

    public GetYoutubeIdFromMovieId(Integer movieId, MovieIdYoutubeIdConvertable context) {
        this.movieId = movieId;
        this.context = context;
    }

    public void initializeMovieIdToYoutubeIdRequest() {
        MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(GetYoutubeIdFromMovieId.this);
        makeReq.execute(String.format("https://api.themoviedb.org/3/movie/%d/videos?api_key=%s&language=%s", this.movieId, GlobalSettings.apiKey, GlobalSettings.language));
    }


    public void ProcessHTTPResponseBody(String HTTPGETResponse) {

        try {
           this.youtubeId = new JSONObject(HTTPGETResponse)
                   .getJSONArray("results")
                   .getJSONObject(0)
                   .getString("key"); // not sure if we should get key or id. Logic says we should get the id, but key looks more like the correct value to me
            context.processMovieIdYoutubeIdConversionResult(this.youtubeId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
