package com.avans.AvansMovieApp.Utilities.NeworkUtilities;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MakeHTTPPOSTRequest extends AsyncTask<String, Integer, String> {

    private String TAG = this.getClass().getSimpleName();
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
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");
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