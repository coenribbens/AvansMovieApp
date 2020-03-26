package com.avans.AvansMovieApp.Utilities.FetchingUtilities;
import android.content.Context;

import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPGETRequest;

public class GetReviews {




        private HTTPRequestable context;

        private String API_ENDPOINT1 = "/movie";
        private int MOVIEID;
        private String API_ENDPOINT2 = "/reviews";
        private String HTTP_GET_PARAMETERS = String.format("?api_key=%s&language=%s&page=1", GlobalVariables.API_KEY_V3, GlobalVariables.LANG);

        public GetReviews(HTTPRequestable context, int MOVIEID) {
            this.context = context;
            MakeHTTPGETRequest makeReq = new MakeHTTPGETRequest(this.context);
            makeReq.execute(GlobalVariables.V3_BASE_URL + API_ENDPOINT1 + "/" + MOVIEID + API_ENDPOINT2 + HTTP_GET_PARAMETERS );

        }


    public String testReqeust(){
     String x =  GlobalVariables.V3_BASE_URL + API_ENDPOINT1 + "/" + MOVIEID + API_ENDPOINT2 + HTTP_GET_PARAMETERS ;
     return x;
    }


    }


