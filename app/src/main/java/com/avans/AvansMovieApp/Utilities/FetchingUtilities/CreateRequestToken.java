package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import android.util.Log;

import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

import org.json.JSONObject;

public class CreateRequestToken implements HTTPRequestable {
    private String TAG = this.getClass().getSimpleName();
    private String API_ENDPOINT = "/authentication/token/new";
    private String HTTP_GET_PARAMETERS = "?api_key=" + GlobalVariables.API_KEY_V3;

    public void initialiseCreateMovieList() {
        String requestURI = GlobalVariables.V3_BASE_URL
                + API_ENDPOINT
                + HTTP_GET_PARAMETERS;

        // Request Body
        JSONObject requestBody = new JSONObject();

        MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(CreateRequestToken.this);
        makeReq.execute(requestURI, requestBody.toString());

    }

        @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {
    Log.d(TAG, HTTPGETResponse);
    String requestToken = "";
    try {
        JSONObject jsonObject = new JSONObject(HTTPGETResponse);
         requestToken = jsonObject.getString("request_token");

    } catch(Exception e) {
        Log.d(TAG, e.toString());
    }
    finally {
        GlobalVariables.setRequestToken(requestToken);
        Log.d(TAG, GlobalVariables.getRequestToken());
        if(GlobalVariables.getGuestSessionID() == null) {
            ValidateUserSession getUserSessionToken = new ValidateUserSession();
            getUserSessionToken.initialiseCreateMovieList();
        }
    }
    }
}
