package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import android.util.Log;

import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPPOSTRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateSession implements HTTPRequestable {
    private String TAG = this.getClass().getSimpleName();
    private String API_ENDPOINT = "/authentication/session/new";
    private String HTTP_GET_PARAMETERS = "?api_key=" + GlobalVariables.API_KEY_V3;

    public void initialiseCreateMovieList() {
        String requestURI = GlobalVariables.V3_BASE_URL
                + API_ENDPOINT
                + HTTP_GET_PARAMETERS;
        Log.d(TAG, requestURI);

        // Request Body
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("request_token", GlobalVariables.REQUEST_TOKEN);
            Log.d(TAG, requestBody.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        MakeHTTPPOSTRequest makeReq = new MakeHTTPPOSTRequest(CreateSession.this);
        makeReq.execute(requestURI, requestBody.toString());

    }

    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {
        Log.d(TAG, HTTPGETResponse);
        String sessionId = "";
        try {
            JSONObject jsonObject = new JSONObject(HTTPGETResponse);
            sessionId = jsonObject.getString("session_id");

        } catch(Exception e) {
            Log.d(TAG, e.toString());
        }
        GlobalVariables.setSessionToken(sessionId);
        CreateMovieList cmv = new CreateMovieList();
        cmv.initialiseCreateMovieList(GlobalVariables.getSessionToken(), "test", "test", "nl");
    }
}
