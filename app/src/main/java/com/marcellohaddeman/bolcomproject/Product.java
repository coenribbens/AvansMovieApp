package com.marcellohaddeman.bolcomproject;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Product {
    private String title;
    private String summary;
    private double price;
    private ArrayList<String> imageURL;

    public Product(String title, String summary, double price, ArrayList<String> imageURL) {
        this.title = title;
        this.summary = summary;
        this.price = price;
        this.imageURL = imageURL;
    }

    @NonNull
    @Override
    public String toString() {
        String result = "title: " + this.title +
                "summary: " + this.summary +
                "price: " + this.price +
                "Images: ";
        for(String item : this.imageURL){
            result += item + "\n";
        }
        return result;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList<String> getImageURL() {
        return imageURL;
    }
}
