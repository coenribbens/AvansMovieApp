package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import android.app.Activity;

import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

public class GetSearchedMovies {

    private HTTPRequestable context;
    private String searchTherm;
    private int page = 1;

    private String API_ENDPOINT = "/search/movie";
    private String HTTP_GET_PARAMETERS = String.format("?api_key=%s&include_adult=true&language=%s&page=%d&query=", GlobalVariables.API_KEY_V3, GlobalVariables.LANG,this.page);


    public GetSearchedMovies(HTTPRequestable context,String searchTherm) {
        this.searchTherm = searchTherm;
        this.context = context;
    }

    public GetSearchedMovies(HTTPRequestable context,Integer page,String searchTherm) {
        this.context = context;
        this.searchTherm = searchTherm;
        this.HTTP_GET_PARAMETERS = String.format("?api_key=%s&include_adult=true&language=%s&page=%d&query=", GlobalVariables.API_KEY_V3, GlobalVariables.LANG,page);

    }

    public void getSearchedMovies() {

        Activity titleSettableContext = (Activity) this.context;
        titleSettableContext.setTitle(this.searchTherm);

        MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(this.context);
        makeReq.execute(GlobalVariables.V3_BASE_URL + API_ENDPOINT + HTTP_GET_PARAMETERS + this.searchTherm );

    }


}
