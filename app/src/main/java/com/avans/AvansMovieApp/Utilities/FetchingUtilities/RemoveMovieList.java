package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import android.util.Log;

import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPPOSTRequest;

import org.json.JSONObject;

public class RemoveMovieList implements HTTPRequestable {
    private String TAG = this.getClass().getSimpleName();
    private String API_ENDPOINT = "/list/";
    private String HTTP_GET_PARAMETERS = "?api_key=" + GlobalVariables.API_KEY_V3;

    public void initialiseCreateMovieList(String userId, String listId) {
        String HTTP_USER_SESSION = userId;
        try {

            String requestURI = GlobalVariables.V3_BASE_URL
                    + API_ENDPOINT
                    + listId
                    + HTTP_GET_PARAMETERS
                    + HTTP_USER_SESSION;

            // Request Body
            JSONObject requestBody = new JSONObject();

            // Post Request
            MakeHTTPPOSTRequest makeReq = new MakeHTTPPOSTRequest(RemoveMovieList.this);
            makeReq.execute(requestURI, requestBody.toString());
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }

    }

    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {
        //Nothing that has to be returned
    }
}