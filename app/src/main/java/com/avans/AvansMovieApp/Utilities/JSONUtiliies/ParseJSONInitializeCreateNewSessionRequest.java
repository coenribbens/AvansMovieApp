package com.avans.AvansMovieApp.Utilities.JSONUtiliies;

import org.json.JSONException;
import org.json.JSONObject;

public class ParseJSONInitializeCreateNewSessionRequest {


    private String rawResponseBody;

    public ParseJSONInitializeCreateNewSessionRequest(String rawResponseBody) {
        this.rawResponseBody = rawResponseBody;
    }

    public String parseSessionToken() throws JSONException {

        return new JSONObject(this.rawResponseBody).getString("request_token");

    }


}
