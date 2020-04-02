package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPPOSTRequest;

public class CreateNewMovieList implements HTTPRequestable {
    private String API_ENDPOINT = "/list";
    private String HTTP_GET_PARAMETERS = "?api_key=" + GlobalVariables.API_KEY_V3;

    public void InitialiseCreateNewMovieList(String postdata){
        MakeHTTPPOSTRequest makeReq = new MakeHTTPPOSTRequest(CreateNewMovieList.this);
        makeReq.execute(GlobalVariables.V3_BASE_URL + API_ENDPOINT + HTTP_GET_PARAMETERS, postdata);
    }

    @Override
    public void ProcessHTTPResponseBody(String HTTPResponseBody) {
        GlobalVariables.setRequestToken(HTTPResponseBody);
    }
}
