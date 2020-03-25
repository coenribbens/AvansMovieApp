package com.avans.AvansMovieApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.avans.AvansMovieApp.Model.Movie;
import com.avans.avans_movie_app.R;
import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity {
    private TextView mTitle;
    private TextView mYear;
    private ImageView mImageView;
    private TextView mOverview;
    private TextView mReleaseDate;
    private TextView mReleaseDateContent;
    private TextView mOriginalLanguage;
    private TextView mOriginalLanguageContent;
    private TextView mRuntime;
    private TextView mRuntimeContent;
    private TextView mGenres;
    private TextView mGenresContent;
    private TextView mProductionCompanies;
    private TextView mProductionCompaniesContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        Movie movie = getIntent().getParcelableExtra("movie");

        //Assigning all the mValues with their view equivalents.
        this.mTitle = findViewById(R.id.tv_movie_detail_title);
        this.mYear = findViewById(R.id.tv_movie_detail_year);
        this.mImageView = findViewById(R.id.iv_movie_detail_image);
        this.mOverview = findViewById(R.id.tv_movie_detail_overview);
        this.mReleaseDate = findViewById(R.id.tv_movie_detail_release_date);
        this.mReleaseDateContent = findViewById(R.id.tv_movie_detail_release_date_content);
        this.mOriginalLanguage = findViewById(R.id.tv_movie_detail_original_language);
        this.mOriginalLanguageContent = findViewById(R.id.tv_movie_detail_original_language_content);
        this.mRuntime = findViewById(R.id.tv_movie_detail_runtime);
        this.mRuntimeContent = findViewById(R.id.tv_movie_detail_runtime_content);
        this.mGenres = findViewById(R.id.tv_movie_detail_genres);
        this.mGenresContent = findViewById(R.id.tv_movie_detail_genres_content);
        this.mProductionCompanies = findViewById(R.id.tv_movie_detail_production_companies);
        this.mProductionCompaniesContent = findViewById(R.id.tv_movie_detail_production_companies_content);

        //Extract all the data from the movie and put it in the corresponding views.
        this.mTitle.setText(movie.getTitle());
        this.mYear.setText(movie.getReleaseDate().getYear());
        Glide.with(this)
                .asBitmap()
                .load(movie.getPosterPath())
                .into(this.mImageView);
        this.mOverview.setText(movie.getOverview());
        this.mReleaseDateContent.setText(movie.getReleaseDate().toString());
        this.mOriginalLanguageContent.setText(movie.getOriginalLanguage());
        this.mRuntimeContent.setText(movie.getRunTime());
        StringBuilder genres = new StringBuilder();
        for(String genre : movie.getGenreNames()){
            if(genres.length() == 0){
                genres.append(genre);
            }
            else {
                genres.append(", ").append(genre);
            }
        }
        this.mGenresContent.setText(genres);
        StringBuilder productionCompanies = new StringBuilder();
        for(String productionCompany : movie.getProductionCompaniesNames()){
            if(productionCompanies.length() == 0){
                productionCompanies.append(productionCompany);
            }
            else {
                productionCompanies.append(", ").append(productionCompany);
            }
        }
        this.mProductionCompaniesContent.setText(productionCompanies);
    }
}
