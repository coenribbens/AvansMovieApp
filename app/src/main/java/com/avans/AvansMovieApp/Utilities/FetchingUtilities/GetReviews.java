package com.avans.AvansMovieApp.Utilities.FetchingUtilities;
import android.content.Context;

import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Model.Review;
import com.avans.AvansMovieApp.Utilities.JSONUtiliies.ParseJSONReviews;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

import org.json.JSONException;

import java.util.ArrayList;

public class GetReviews implements HTTPRequestable {
        private MovieReviewsConvertable context;

        private String API_ENDPOINT1 = "/movie";
        private int MOVIEID;
        private String API_ENDPOINT2 = "/reviews";
        private String HTTP_GET_PARAMETERS = String.format("?api_key=%s&language=%s&page=1", GlobalVariables.API_KEY_V3, GlobalVariables.LANG);
        private String Fullrequest;

        public GetReviews(MovieReviewsConvertable context, int MOVIEID) {
            this.context = context;
            MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(GetReviews.this);
            makeReq.execute(GlobalVariables.V3_BASE_URL + API_ENDPOINT1 + "/" + MOVIEID + API_ENDPOINT2 + HTTP_GET_PARAMETERS );

        }

    public GetReviews(int MOVIEID) {

        MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(GetReviews.this);
        makeReq.execute(GlobalVariables.V3_BASE_URL + API_ENDPOINT1 + "/" + MOVIEID + API_ENDPOINT2 + HTTP_GET_PARAMETERS );
        Fullrequest = GlobalVariables.V3_BASE_URL + API_ENDPOINT1 + "/" + MOVIEID + API_ENDPOINT2 + HTTP_GET_PARAMETERS;

    }


    public String GetRequestString(){
    return this.Fullrequest;
    }


    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {
        ParseJSONReviews pjr = new ParseJSONReviews(HTTPGETResponse);
        try {
            pjr.fetchReviews();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<Review> reviews = pjr.getReviews();
        context.processMovieReviewsConversionResult(reviews);
    }
}


