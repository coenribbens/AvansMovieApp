package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import com.avans.AvansMovieApp.Model.Review;

import java.util.ArrayList;

public interface MovieReviewsConvertable {
    void processMovieReviewsConversionResult(ArrayList<Review> reviews);
}
