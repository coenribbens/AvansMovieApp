package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import android.util.Log;

import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Model.Review;
import com.avans.AvansMovieApp.Utilities.JSONUtiliies.ParseJSONGeustSessionToken;
import com.avans.AvansMovieApp.Utilities.JSONUtiliies.ParseJSONInitializeCreateNewSessionRequest;
import com.avans.AvansMovieApp.Utilities.JSONUtiliies.ParseJSONReviews;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

import org.json.JSONException;

import java.util.ArrayList;


public class FetchGeustSessionToken implements HTTPRequestable {

    private String APIkey = GlobalVariables.API_KEY_V3;
    private String V3BaseURL = GlobalVariables.V3_BASE_URL;
    private String AuthenthicationgeustSession = "/authentication/guest_session/new?api_key=";

    public void initializeCreateNewGeustSessionRequest() {
        MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(FetchGeustSessionToken.this);
        makeReq.execute(V3BaseURL + AuthenthicationgeustSession + APIkey);



    }

    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {
        // set a new session to GlobalSettings
        try {
            ParseJSONGeustSessionToken parseJSONGeustSessionToken = new ParseJSONGeustSessionToken(HTTPGETResponse);
            String GeustsessionToken = parseJSONGeustSessionToken.FetchGeustSessionToken();

            GlobalVariables.setGuestSessionId(GeustsessionToken);
            Log.v("GeustSessionID is" , GlobalVariables.GetGeustSessionID());



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    }





