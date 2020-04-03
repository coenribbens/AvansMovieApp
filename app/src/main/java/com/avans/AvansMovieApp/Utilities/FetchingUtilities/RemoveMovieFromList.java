package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import android.util.Log;

import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPPOSTRequest;

import org.json.JSONObject;

public class RemoveMovieFromList implements HTTPRequestable {
    private String TAG = this.getClass().getSimpleName();
    private String API_ENDPOINT = "/list/";
    private String HTTP_GET_PARAMETERS = "?api_key=" + GlobalVariables.API_KEY_V3;
    private String HTTP_SESSION_ID = "&session_id=" + GlobalVariables.getSessionToken();

    public void initialiseCreateMovieList(String userId, String listId, String movieId) {
        String HTTP_USER_SESSION = userId;
        try {

            String requestURI = GlobalVariables.V3_BASE_URL
                    + API_ENDPOINT
                    + listId
                    + "/remove_item"
                    + HTTP_GET_PARAMETERS
                    + HTTP_SESSION_ID;
            Log.d(TAG, requestURI);

            // Request Body
            JSONObject requestBody = new JSONObject();
            requestBody.put("media_id", movieId);

            // Post Request
            MakeHTTPPOSTRequest makeReq = new MakeHTTPPOSTRequest(RemoveMovieFromList.this);
            makeReq.execute(requestURI, requestBody.toString());
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }

    }

    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {

    }
}
