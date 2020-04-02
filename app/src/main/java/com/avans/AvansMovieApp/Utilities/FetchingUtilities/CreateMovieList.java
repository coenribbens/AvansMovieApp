package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import android.util.Log;

import com.avans.AvansMovieApp.Datalayer.MovieDBHandler;
import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPPOSTRequest;

import org.json.JSONObject;

public class CreateMovieList implements HTTPRequestable {
    private String TAG = this.getClass().getSimpleName();
    private String API_ENDPOINT = "/list";
    private String HTTP_GET_PARAMETERS = "?api_key=" + GlobalVariables.API_KEY_V3;
    private String HTTP_USER_SESSION = null;


    public void initialiseCreateMovieList(String userId, String name, String description, String language) {
        HTTP_USER_SESSION = "&session_id=" + GlobalVariables.getGuestSessionID();
        try {

            String requestURI = GlobalVariables.V3_BASE_URL
                    + API_ENDPOINT
                    + HTTP_GET_PARAMETERS
                    + HTTP_USER_SESSION;

            // Request Body
            JSONObject requestBody = new JSONObject();
            requestBody.put("name", name);
            requestBody.put("description", description);
            requestBody.put("language", language);


            // Post Request
            MakeHTTPPOSTRequest makeReq = new MakeHTTPPOSTRequest(CreateMovieList.this);
            Log.d(TAG, requestBody.toString());
            Log.d(TAG, requestURI);
            Log.d(TAG, "------------------------------------------------------------------------------------------------");
            makeReq.execute(requestURI, requestBody.toString());
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }

    }

    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {
    Log.d(TAG, HTTPGETResponse);

    }
}