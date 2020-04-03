package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import android.util.Log;

import com.avans.AvansMovieApp.Datalayer.MovieDBHandler;
import com.avans.AvansMovieApp.Model.CompactMovie;
import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPPOSTRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetListDetails implements HTTPRequestable {
    private String userId;
    private String listId;
    private ListDetailsConvertable context;
    MovieDBHandler db;
    private String TAG = this.getClass().getSimpleName();
    private String API_ENDPOINT = "/list/";
    private String HTTP_GET_PARAMETERS = String.format("?api_key=%s&language=%s&page=20", GlobalVariables.API_KEY_V3, GlobalVariables.LANG);
    private String HTTP_USER_SESSION = "&session_id=" + GlobalVariables.getSessionToken();
    private ArrayList<CompactMovie> compactMovies = new ArrayList<CompactMovie>();
    private String rawResponseBody;

    public GetListDetails(ListDetailsConvertable context){
        this.context = context;
    }

    public void initialiseCreateMovieList(String listId) {
        try {
            String requestURI = GlobalVariables.V3_BASE_URL
                    + API_ENDPOINT
                    + listId
                    + HTTP_GET_PARAMETERS
                    + HTTP_USER_SESSION;

            Log.d(TAG, requestURI);
            // Request Body
            JSONObject requestBody = new JSONObject();
            requestBody.put("list_id", listId);

            MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(GetListDetails.this);
            makeReq.execute(requestURI, requestBody.toString());
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }

    }


    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {
        Log.d(TAG, HTTPGETResponse);

        try {
            JSONObject parentObject = new JSONObject(HTTPGETResponse);
            JSONArray moviesArr = parentObject.getJSONArray("items");

            for (int i = 0; i < moviesArr.length(); ++i) {
                final JSONObject movieJSONObj = moviesArr.getJSONObject(i);

                // coverting genreIds to a real arraylist
                JSONArray JSONgenreIds = movieJSONObj.getJSONArray("genre_ids");
                ArrayList<Integer> genreIds = new ArrayList<Integer>();

                for (int ii = 0; ii < JSONgenreIds.length(); ++ii) {
                    genreIds.add(JSONgenreIds.getInt(ii));
                }

                compactMovies.add(
                        new CompactMovie(
                                movieJSONObj.getInt("popularity"),
                                movieJSONObj.getInt("vote_count"),
                                movieJSONObj.getBoolean("video"),
                                movieJSONObj.getString("poster_path"),
                                movieJSONObj.getInt("id"),
                                movieJSONObj.getBoolean("adult"),
                                movieJSONObj.getString("backdrop_path"),
                                movieJSONObj.getString("original_language"),
                                movieJSONObj.getString("original_title"),
                                genreIds,
                                movieJSONObj.getString("title"),
                                movieJSONObj.getInt("vote_average"),
                                movieJSONObj.getString("overview"),
                                movieJSONObj.getString("release_date")
                        )
                );
            }
            this.context.processListDetailsConvertableResult(compactMovies);
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
    }
}
