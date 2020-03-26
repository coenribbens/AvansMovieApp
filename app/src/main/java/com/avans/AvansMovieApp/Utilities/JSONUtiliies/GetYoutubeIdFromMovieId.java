package com.avans.AvansMovieApp.Utilities.JSONUtiliies;

import com.avans.AvansMovieApp.Model.GlobbalConstants;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class GetYoutubeIdFromMovieId implements HTTPRequestable {

    private Integer movieId;
    private String youtubeId;
    private MovieIdYoutubeIdConvertable context;

    private String API_ENDPOINT = "/movie/%s/videos";
    private String HTTP_GET_PARAMETERS = String.format("?api_key=%s&language=%s", GlobbalConstants.API_KEY_V3, GlobbalConstants.LANG);


    public GetYoutubeIdFromMovieId(Integer movieId, MovieIdYoutubeIdConvertable context) {
        this.movieId = movieId;
        this.context = context;
    }

    public void initializeMovieIdToYoutubeIdRequest() {
        MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(GetYoutubeIdFromMovieId.this);
        makeReq.execute(String.format(GlobbalConstants.V3_BASE_URL + API_ENDPOINT + HTTP_GET_PARAMETERS, this.movieId ));
    }


    public void ProcessHTTPResponseBody(String HTTPGETResponse) {

        try {
           ParseJSONYoutubeId parser = new ParseJSONYoutubeId(HTTPGETResponse);
           this.youtubeId =  parser.parseYoutubeId();
            context.processMovieIdYoutubeIdConversionResult(this.youtubeId);
        } catch (JSONException e) {
            context.processMovieIdYoutubeIdConversionResult(null);
        }

    }


}
