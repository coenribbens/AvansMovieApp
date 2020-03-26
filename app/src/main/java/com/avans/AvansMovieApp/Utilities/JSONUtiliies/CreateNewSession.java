package com.avans.AvansMovieApp.Utilities.JSONUtiliies;

import com.avans.AvansMovieApp.Model.GlobbalConstants;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

import org.json.JSONException;

public class CreateNewSession implements HTTPRequestable {


    private String API_ENDPOINT = "/authentication/token/new";
    private String HTTP_GET_PARAMETERS = "?api_key=" + GlobbalConstants.API_KEY_V3;



    public void initializeCreateNewSessionRequest() {
        MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(CreateNewSession.this);
        makeReq.execute(GlobbalConstants.V3_BASE_URL + API_ENDPOINT + HTTP_GET_PARAMETERS);
    }



    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {

        // set a new session to GlobalSettings
        try {
            ParseJSONInitializeCreateNewSessionRequest parser = new ParseJSONInitializeCreateNewSessionRequest(HTTPGETResponse);
            String sessionToken = parser.parseSessionToken();
            GlobbalConstants.setSessionToken(sessionToken);

            // TODO: IDEA, Show a toast with "authenticated"


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
