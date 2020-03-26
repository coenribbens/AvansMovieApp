package com.avans.AvansMovieApp.Utilities.JSONUtiliies;

import android.util.Log;
import android.widget.Toast;

import com.avans.AvansMovieApp.MainActivity;
import com.avans.AvansMovieApp.Model.GlobalSettings;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

import org.json.JSONException;

public class CreateNewSession implements HTTPRequestable {



    public void initializeCreateNewSessionRequest() {
        MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(CreateNewSession.this);
        makeReq.execute(String.format("https://api.themoviedb.org/3/authentication/token/new?api_key=%s", GlobalSettings.apiKeyV3));
    }



    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {

        // set a new session to GlobalSettings
        try {
            ParseJSONInitializeCreateNewSessionRequest parser = new ParseJSONInitializeCreateNewSessionRequest(HTTPGETResponse);
            String sessionToken = parser.parseSessionToken();
            GlobalSettings.setSessionToken(sessionToken);

            // TODO: IDEA, Show a toast with "authenticated"


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
