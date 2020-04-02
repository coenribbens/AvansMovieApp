package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.util.Log;

import com.avans.AvansMovieApp.Datalayer.MovieDBHandler;
import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Model.ListMovie;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class MovieList implements HTTPRequestable {
    private String TAG = this.getClass().getSimpleName();
    private Context movieListContext;

    private String userId;
    private String listId;
    private String listName;
    private String description;
    MovieDBHandler db;

    private String API_ENDPOINT = "/account/";
    private String API_LISTS = "/lists";
    private String HTTP_GET_PARAMETERS = String.format("?api_key=%s&language=%s&page=20", GlobalVariables.API_KEY_V3, GlobalVariables.LANG);

    public MovieList(String userId, String listId) {
        this.userId = userId;
        this.listId = listId;
    }

    public void initialiseGetListRequest() {
       userId = db.getGuestToken();
        MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(MovieList.this);
        makeReq.execute(GlobalVariables.V3_BASE_URL + API_ENDPOINT + userId + API_LISTS + HTTP_GET_PARAMETERS);
    }


    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {
    try {
        JSONObject JSONMovieListItems = new JSONObject(HTTPGETResponse);
        JSONArray features = JSONMovieListItems.getJSONArray("results");

        for(int i = 0; i < features.length(); i++) {
            JSONObject feature = features.getJSONObject(i);

        Log.d(TAG, features.toString());

             int id = feature.getJSONObject("results").getInt("id");
             String listName = feature.getJSONObject("results").getString("name");
             String description = feature.getJSONObject("results").getString("description");
             String posterPath = feature.getJSONObject("results").getString("poster_path");
             int itemCount = feature.getJSONObject("results").getInt("item_count");
             String language = feature.getJSONObject("results").getString("iso_639_1");

             ListMovie lm = new ListMovie(id, listName, description, posterPath, itemCount, language);


        }

    }catch(Exception e) {
        Log.d(TAG , e.toString());
    }
    }
}
