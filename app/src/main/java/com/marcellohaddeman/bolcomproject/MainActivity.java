package com.marcellohaddeman.bolcomproject;

import android.app.Person;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText searchBar;
    private Button searchButton;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Objecten initialiseren
        this.searchBar = findViewById(R.id.search_bar);
        this.searchButton = findViewById(R.id.btn_search_button);
        this.recyclerView = findViewById(R.id.rv_product_items);


        //OnClick voor de searchknop activeren.
        this.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchSearchResults fetchSearchResults = new FetchSearchResults();

                fetchSearchResults.execute(NetworkUtils.buildURL(searchBar.getText().toString()));
            }
        });
    }

    protected void productsReceived(ArrayList<Product> products){
        //TODO de products received in een arraylist van producten veranderen en deze naar de RecycleViewAdapter sturen.
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
}

