package com.avans.AvansMovieApp.Utilities.NeworkUtilities;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MakeHTTPPOSTRequest extends AsyncTask<String, Integer, String> {

    private HTTPRequestable context;

    public MakeHTTPPOSTRequest(HTTPRequestable context) {
        this.context = context;
    }


    protected String doInBackground(String... args) {
        String response = "";
        try {
            URL url = new URL(args[0]);
            String data = args[1]; // this is a JSON object

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            InputStream stream = conn.getInputStream();
            Scanner s = new Scanner(stream).useDelimiter("\\A");
            response += s.hasNext() ? s.next() : "";
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    protected void onPostExecute(String responseBody) {
        context.ProcessHTTPResponseBody(responseBody);
    }
}