package com.avans.AvansMovieApp.Utilities.JSONUtiliies;

import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

import org.json.JSONException;

public class GetYoutubeIdFromMovieId implements HTTPRequestable {

    private Integer movieId;
    private String youtubeId;
    private MovieIdYoutubeIdConvertable context;

    private String API_ENDPOINT = "/movie/%s/videos";
    private String HTTP_GET_PARAMETERS = String.format("?api_key=%s&language=%s", GlobalVariables.API_KEY_V3, GlobalVariables.LANG);


    public GetYoutubeIdFromMovieId(Integer movieId, MovieIdYoutubeIdConvertable context) {
        this.movieId = movieId;
        this.context = context;
    }

    public void initializeMovieIdToYoutubeIdRequest() {
        MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(GetYoutubeIdFromMovieId.this);
        makeReq.execute(String.format(GlobalVariables.V3_BASE_URL + API_ENDPOINT + HTTP_GET_PARAMETERS, this.movieId ));
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
