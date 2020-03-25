package com.avans.AvansMovieApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.avans.AvansMovieApp.Model.DetailedMovie;
import com.avans.AvansMovieApp.Utilities.JSONUtiliies.GetDetailedMovieFromMovieId;
import com.avans.AvansMovieApp.Utilities.JSONUtiliies.MovieIdDetailedMovieConvertable;
import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity implements MovieIdDetailedMovieConvertable {
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
    private ImageButton mShare;

    private DetailedMovie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        int movieId = getIntent().getIntExtra("movieid", 0);
        GetDetailedMovieFromMovieId getDetailedMovieFromMovieId = new GetDetailedMovieFromMovieId(movieId, this);
        getDetailedMovieFromMovieId.initializeMovieIdToDetailedMovieRequest();


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
        this.mShare = findViewById(R.id.ib_movie_detail_share);

        //Add logic for the share button.
        this.mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out this movie!\n" +
                        movie.getTitle() + "\n" +
                        movie.getHomepage());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);

            }
        });

    }

    @Override
    public void processConversionResult(DetailedMovie detailedMovie) {
        this.movie = detailedMovie;
        //Extract all the data from the movie and put it in the corresponding views.
        this.mTitle.setText(movie.getTitle());
        this.mYear.setText(movie.getReleaseDate());
        Glide.with(this)
                .asBitmap()
                .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movie.getPosterPath())
                .into(this.mImageView);
        this.mOverview.setText(movie.getOverview());
        this.mReleaseDateContent.setText(movie.getReleaseDate().toString());
        this.mOriginalLanguageContent.setText(movie.getOriginalLanguage());
        this.mRuntimeContent.setText(movie.getRunTime() + "");
        if(movie.getGenreNames() != null){
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
        }
        if (movie.getProductionCompaniesNames() != null){
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
}