package com.avans.AvansMovieApp;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String BOL_BASE_URL = "https://api.bol.com/catalog/v4/search";
    private final static String apiKey = "25C4742A92BF468EB2BD888FC8FBFF40";
    private static final String format = "json";
    private static final String limit = "20";

    final static String API_KEY_PARAM = "apikey";
    final static String FORMAT_PARAM = "format";
    final static String QUERY_PARAM = "q";
    final static String LIMIT_PARAM = "limit";



    public static URL buildURL(String searchTerm){
        Uri builtUri = Uri.parse(BOL_BASE_URL).buildUpon()
                .appendQueryParameter(API_KEY_PARAM, apiKey)
                .appendQueryParameter(FORMAT_PARAM, format)
                .appendQueryParameter(LIMIT_PARAM, limit)
                .appendQueryParameter(QUERY_PARAM, searchTerm)
                .build();

        URL url = null;
        try{
            url = new URL(builtUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            if(scanner.hasNext()){
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
