package com.avans.AvansMovieApp.Utilities.NeworkUtilities;

import android.os.AsyncTask;
import android.util.Log;

import com.avans.AvansMovieApp.Utilities.FetchingUtilities.RemoveMovieList;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MakeHTTPDeleteRequest extends AsyncTask<String, Integer, String> {
    private String TAG = this.getClass().getSimpleName();
    private HTTPRequestable context;
    private HttpURLConnection conn;

    public MakeHTTPDeleteRequest(HTTPRequestable  context) {
        this.context = context;
    }


    @Override
    protected String doInBackground(String... args) {

        String response = "";
        try {
            URL url = new URL(args[0]);
            String data = args[1]; // this is a JSON object

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("DELETE");
            conn.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();


            InputStream is = conn.getInputStream();
            BufferedReader in = null;
            String inputLine;
            StringBuilder body;
            in = new BufferedReader(new InputStreamReader(is));

            body = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                body.append(inputLine);
            }
            in.close();
            is.close();
            response = body.toString();
            Log.d(TAG, response);

        } catch (Exception e) {
        Log.d(TAG, e.toString());
        }

        return response;
    }
}
