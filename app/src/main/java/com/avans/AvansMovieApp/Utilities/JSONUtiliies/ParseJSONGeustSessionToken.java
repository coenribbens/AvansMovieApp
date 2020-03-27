package com.avans.AvansMovieApp.Utilities.JSONUtiliies;

import com.avans.AvansMovieApp.Model.GlobalVariables;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;

public class ParseJSONGeustSessionToken{

    private  String rawResponse;
    private String GeustSessionToken;

    public ParseJSONGeustSessionToken(String HTTPResponse){
        this.rawResponse = HTTPResponse;
    }
    public String FetchGeustSessionToken() throws JSONException{
        JSONObject parentObject =  new JSONObject(this.rawResponse);

        GeustSessionToken = parentObject.getString("guest_session_id");


        return GeustSessionToken;

    }




}

