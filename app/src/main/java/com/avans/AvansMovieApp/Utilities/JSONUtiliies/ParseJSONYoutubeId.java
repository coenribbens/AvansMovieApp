package com.avans.AvansMovieApp.Utilities.JSONUtiliies;

import org.json.JSONException;
import org.json.JSONObject;

public class ParseJSONYoutubeId {

    private String rawResponseBody;

    public ParseJSONYoutubeId(String rawResponseBody) {
        this.rawResponseBody = rawResponseBody;
    }


    public String parseYoutubeId() throws JSONException {

        return new JSONObject(this.rawResponseBody)
                .getJSONArray("results")
                .getJSONObject(0)
                .getString("key");

    }

}
