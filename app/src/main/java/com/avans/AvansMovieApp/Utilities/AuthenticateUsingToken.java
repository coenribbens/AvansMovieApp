package com.avans.AvansMovieApp.Utilities;

import android.util.Log;

import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPPOSTRequest;

public class AuthenticateUsingToken implements HTTPRequestable {


    private String API_ENDPOINT = "/authentication/token/new";
    private String HTTP_GET_PARAMETERS = "?api_key=" + GlobalVariables.API_KEY_V3;


    public void initializeAuthenticatingUsingToken(String postdata){
        MakeHTTPPOSTRequest makeReq = new MakeHTTPPOSTRequest(AuthenticateUsingToken.this);
        makeReq.execute(GlobalVariables.V3_BASE_URL + API_ENDPOINT + HTTP_GET_PARAMETERS,postdata);
    }

    @Override
    public void ProcessHTTPResponseBody(String HTTPResponseBody) {
        GlobalVariables.setRequestToken(HTTPResponseBody);
    }
}
