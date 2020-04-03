package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import android.util.Log;

import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Model.ListMovie;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieList implements HTTPRequestable {
    private String TAG = this.getClass().getSimpleName();
    private MovieListsConvertable context;
    private ListMovie listMovie;

    private String userId;
    private String listName;
    private String description;


    private String API_ENDPOINT = "/account/";
    private String USER_ID = "{account_id}";
    private String API_LISTS = "/lists";
    private String HTTP_GET_PARAMETERS = String.format("?api_key=%s&language=%s&page=20&session_id=%s", GlobalVariables.API_KEY_V3, GlobalVariables.LANG, GlobalVariables.getSessionToken());

    public MovieList(String userId, MovieListsConvertable context) {
        this.userId = userId;
        this.context = context;
    }
    ArrayList<ListMovie> listMovies = new ArrayList<ListMovie>();

    public void initialiseGetListRequest() {
        Log.d(TAG, HTTP_GET_PARAMETERS);
        MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(MovieList.this);
        makeReq.execute(GlobalVariables.V3_BASE_URL + API_ENDPOINT + this.userId + API_LISTS + HTTP_GET_PARAMETERS);
    }


    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {
    Log.d(TAG, HTTPGETResponse);
    try {
        JSONObject JSONMovieListItems = new JSONObject(HTTPGETResponse);
        JSONArray features = JSONMovieListItems.getJSONArray("results");

        for(int i = 0; i < features.length(); i++) {
            JSONObject feature = features.getJSONObject(i);

        Log.d(TAG, features.toString());

             int id = feature.getInt("id");
             String listName = feature.getString("name");
             String description = feature.getString("description");
             String posterPath = feature.getString("poster_path");
             int itemCount = feature.getInt("item_count");
             String language = feature.getString("iso_639_1");

             ListMovie lm = new ListMovie(id, listName, description, posterPath, itemCount, language);
            listMovies.add(lm);

        }

    }catch(Exception e) {
        Log.d(TAG , e.toString());
    }
    context.processMovieListConvertableResult(this.listMovies);
    }
}
