package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import android.util.Log;

import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPPOSTRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ValidateUserSession implements HTTPRequestable {
    private String TAG = this.getClass().getSimpleName();
    private String API_ENDPOINT = "/authentication/token/validate_with_login";
    private String HTTP_GET_PARAMETERS = "?api_key=" + GlobalVariables.API_KEY_V3;

    public void initialiseCreateMovieList() {
        String requestURI = GlobalVariables.V3_BASE_URL
                + API_ENDPOINT
                + HTTP_GET_PARAMETERS;

        // Request Body
        JSONObject requestBody = new JSONObject();


        try {
            requestBody.put("username", "cribbens");
            requestBody.put("password", "thisisapassword");
            requestBody.put("request_token", GlobalVariables.getRequestToken());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MakeHTTPPOSTRequest makeReq = new MakeHTTPPOSTRequest(ValidateUserSession.this);
        makeReq.execute(requestURI, requestBody.toString());
        Log.d(TAG, requestURI);
        Log.d(TAG, requestBody.toString());
    }

    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {
        Log.d(TAG, HTTPGETResponse);
        Log.d(TAG, "----------------------------------------------------------------------");
        CreateSession createSession = new CreateSession();
        createSession.initialiseCreateMovieList();
    }
}
