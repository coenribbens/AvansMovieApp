package com.avans.AvansMovieApp.Utilities.NeworkUtilities;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MakeHTTPPOSTRequest extends AsyncTask<String, Integer, String> {

    private HTTPRequestable context;
    private HttpURLConnection conn;


    public MakeHTTPPOSTRequest(HTTPRequestable context) {
        this.context = context;
    }


    protected String doInBackground(String... args) {
        String response = "";
        try {
            URL url = new URL(args[0]);
            String data = args[1]; // this is a JSON object

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.getOutputStream().write(data.getBytes("UTF-8"));
            InputStream stream = conn.getInputStream();
            Scanner s = new Scanner(stream).useDelimiter("\\A");
            response += s.hasNext() ? s.next() : "";
            s.close();
        } catch (IOException e) {
            try {
                Log.v("RespCode",""+conn.getResponseCode());
                // loooool, fucking java is sooo fuckig great lol. An exception in an exception to catch data from the exception. Lmaooooooooooooooooooooooooooooooooooooooooooooooooooo
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