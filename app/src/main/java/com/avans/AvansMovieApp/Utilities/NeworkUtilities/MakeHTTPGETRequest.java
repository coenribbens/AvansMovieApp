package com.avans.AvansMovieApp.Utilities.NeworkUtilities;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MakeHTTPGETRequest extends AsyncTask<String, Integer, String> {

    private HTTPRequestable context;
    private HttpURLConnection conn;


    public MakeHTTPGETRequest(HTTPRequestable context) {
        this.context = context;
    }

    protected String doInBackground(String... urls) {
        String response = "";
        try {
            URL url = new URL(urls[0]);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream stream = conn.getInputStream();
            Scanner s = new Scanner(stream).useDelimiter("\\A");
            response += s.hasNext() ? s.next() : "";
            s.close();
        } catch (IOException e) {
            try {
                Log.v("RespCode",""+conn.getResponseCode());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return response;
    }

    protected void onPostExecute(String responseBody) {
        context.ProcessHTTPResponseBody(responseBody);
    }
}