package com.avans.AvansMovieApp.Utilities;

import com.avans.AvansMovieApp.Model.GlobbalConstants;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPPOSTRequest;

public class AuthenticateUsingToken implements HTTPRequestable {


    private String API_ENDPOINT = "/authentication/token/new";
    private String HTTP_GET_PARAMETERS = "?api_key=" + GlobbalConstants.API_KEY_V3;


    public void initializeAuthenticatingUsingToken(){
        MakeHTTPPOSTRequest makeReq = new MakeHTTPPOSTRequest(AuthenticateUsingToken.this);
        makeReq.execute(GlobbalConstants.V3_BASE_URL + API_ENDPOINT + HTTP_GET_PARAMETERS);

    }

    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {

    }
}
