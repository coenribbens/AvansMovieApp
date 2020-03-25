package com.avans.AvansMovieApp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.avans.AvansMovieApp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private EditText searchBar;
    private Button searchButton;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //



    }

    /*
    protected void productsReceived(ArrayList<Product> products){
        RecycleViewAdapter recycleViewAdapter = new RecycleViewAdapter(products, this);
        this.recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    protected class FetchSearchResults extends AsyncTask<URL, Void, ArrayList<Product>>{



        @Override
        protected ArrayList<Product> doInBackground(URL... urls) {

            String jsonProductResponse = null;
            ArrayList<Product> productArrayList = new ArrayList<>();

            try{
                //TODO Afmaken afhandeling van de JSON string.
                jsonProductResponse = NetworkUtils
                        .getResponseFromHttpUrl(urls[0]);

                //JSON Parsing
                //Classes aanmaken
                JSONObject jsonRootObject = new JSONObject(jsonProductResponse);
                JSONArray jsonProductsArray = jsonRootObject.getJSONArray("products");
                JSONObject jsonProductObject;
                JSONArray jsonProductImages;

                //Informatie uit de JSON halen
                for(int x = 0; x < jsonProductsArray.length();x++){
                    jsonProductObject = jsonProductsArray.getJSONObject(x);
                    String productName = jsonProductObject.getString("title");
                    String productSummary = jsonProductObject.getString("summary");
                    double productPrice = jsonProductObject.getJSONObject("offerData")
                            .getJSONArray("offers")
                            .getJSONObject(0).getDouble("price");

                    ArrayList<String> productImages = new ArrayList<>();
                    jsonProductImages = jsonProductObject.getJSONArray("images");

                    for(int y = 0; y < jsonProductImages.length(); y++){
                        productImages.add(jsonProductImages.getJSONObject(y).getString("url"));
                    }
                    productArrayList.add(new Product(productName, productSummary, productPrice, productImages));
                }

            } catch (Exception e){
                e.printStackTrace();
            }
            return productArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<Product> products) {
            productsReceived(products);
        }
    }
    */
}


