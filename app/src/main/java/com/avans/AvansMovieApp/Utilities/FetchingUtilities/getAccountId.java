package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class getAccountId implements HTTPRequestable {
    private String TAG = this.getClass().getSimpleName();
    private String API_ENDPOINT = "/account";
    private String HTTP_GET_PARAMETERS = "?api_key=" + GlobalVariables.API_KEY_V3;
    private String HTTP_USER_SESSION = "&session_id=" + GlobalVariables.getSessionToken();

    public void initialiseCreateMovieList() {
        String requestURI = GlobalVariables.V3_BASE_URL
                + API_ENDPOINT
                + HTTP_GET_PARAMETERS
                + HTTP_USER_SESSION;

        // Request Body
        JSONObject requestBody = new JSONObject();

        MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(getAccountId.this);
        makeReq.execute(requestURI, requestBody.toString());

    }
    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {
        String accountId;
        try {
            JSONObject JSONMovieListItems = new JSONObject(HTTPGETResponse);

            accountId = JSONMovieListItems.getString("id");
            GlobalVariables.setUSERID(accountId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
