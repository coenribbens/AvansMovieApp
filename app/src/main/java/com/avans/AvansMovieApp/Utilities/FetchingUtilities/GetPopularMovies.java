package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

public class GetPopularMovies  {

    private HTTPRequestable context;

    private String API_ENDPOINT = "/movie/popular";
    private String HTTP_GET_PARAMETERS = String.format("?api_key=%s&language=%s&page=1", GlobalVariables.API_KEY_V3, GlobalVariables.LANG);


    public GetPopularMovies(HTTPRequestable context) {
        this.context = context;
    }

    public GetPopularMovies(HTTPRequestable context,Integer page) {
        this.context = context;
        this.HTTP_GET_PARAMETERS = String.format("?api_key=%s&language=%s&page=%d", GlobalVariables.API_KEY_V3, GlobalVariables.LANG,page);
    }


    public void getPopularMovies() {
        MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(this.context);
        makeReq.execute(GlobalVariables.V3_BASE_URL + API_ENDPOINT + HTTP_GET_PARAMETERS);
    }
}
