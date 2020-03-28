package com.avans.AvansMovieApp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.avans.AvansMovieApp.Adapters.ReviewAdapter;
import com.avans.AvansMovieApp.Model.DetailedMovie;
import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.Model.Review;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.GetDetailedMovieFromMovieId;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.GetReviews;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.GetYoutubeIdFromMovieId;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.MovieIdDetailedMovieConvertable;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.MovieIdYoutubeIdConvertable;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.MovieReviewsConvertable;
import com.avans.AvansMovieApp.Utilities.FetchingUtilities.PostTokenAndAuthenticate;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.HTTPRequestable;
import com.avans.AvansMovieApp.Utilities.NeworkUtilities.MakeHTTPPOSTRequest;
import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MovieDetailActivity extends AppCompatActivity implements MovieIdDetailedMovieConvertable, MovieIdYoutubeIdConvertable, MovieReviewsConvertable, HTTPRequestable {
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
    private ImageButton mTrailer;
    private ListView mListview;
    private DetailedMovie movie;
    //private ReviewAdapter mReviewAdapter;
    //private ArrayList<Review> mReviewList = new ArrayList<>();
    private RatingBar mRatingBar;
    private float mRating;

    private String API_ENDPOINT = "/movie";
    private String HTTPParameters = "/%d/rating?api_key=%s&session_id=%s";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        int movieId = getIntent().getIntExtra("movieid", 0);
        GetDetailedMovieFromMovieId getDetailedMovieFromMovieId = new GetDetailedMovieFromMovieId(movieId, this);
        getDetailedMovieFromMovieId.initializeMovieIdToDetailedMovieRequest();



        GetYoutubeIdFromMovieId getYoutubeIdFromMovieId = new GetYoutubeIdFromMovieId(movieId,this);
        getYoutubeIdFromMovieId.initializeMovieIdToYoutubeIdRequest();

        GetReviews getReviews = new GetReviews(this, movieId);


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
        this.mTrailer = findViewById(R.id.ib_movie_detail_youtube);
        //this.mListview = findViewById(R.id.ib_listview_review);
        this.mRatingBar = findViewById(R.id.rb_rating_bar);


        //this.mReviewAdapter = new ReviewAdapter(this, R.layout.review_item, mReviewList);
        //this.mListview.setAdapter(mReviewAdapter);
        //Add logic for the share button.
        this.mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.sharing_text) +
                        movie.getTitle() + "\n" +
                        movie.getHomepage());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);

            }
        });

        mRatingBar = findViewById(R.id.rb_rating_bar);
        mRating = mRatingBar.getRating() * 2;

        // get a token first
        // https://api.themoviedb.org/3/movie/1/rating?api_key=b966d45d0ab662f523ce11044a9394ef

        String JSONPostData = String.format("{'value':%.2f}",mRating).replace(",", ".");;



        // TODO: Do this on button press
        // TODO: this currently doesn't work, this is due to the SESSION_TOKEN being broken in general. Someone, go fix this and it'll work. also check is mRating > .5
        MakeHTTPPOSTRequest makeReq = new MakeHTTPPOSTRequest(MovieDetailActivity.this);
        Log.v("{{URL}}",GlobalVariables.V3_BASE_URL + API_ENDPOINT  + String.format(HTTPParameters,movieId,GlobalVariables.API_KEY_V3,GlobalVariables.SESSION_TOKEN));
        makeReq.execute(GlobalVariables.V3_BASE_URL + API_ENDPOINT  + String.format(HTTPParameters,movieId,GlobalVariables.API_KEY_V3,GlobalVariables.SESSION_TOKEN),JSONPostData);
        // end
    }

    @Override
    public void processMovieIdYoutubeIdConversionResult(final String youtubeId){


        if( youtubeId != null){
        this.mTrailer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + youtubeId));
                intent.putExtra("VIDEO_ID", youtubeId);
                startActivity(intent);
                }
            }
        );
        }else{
            this.mTrailer.setVisibility(View.GONE);
        }



    }

    @Override
    public void processMovieIdDetailedMovieConversionResult(DetailedMovie detailedMovie) {
        this.movie = detailedMovie;
        //Extract all the data from the movie and put it in the corresponding views.
        this.mTitle.setText(movie.getTitle());
        setTitle(getResources().getText(R.string.mov_detail_ac_title) + this.movie.getTitle());
        this.mYear.setText(movie.getReleaseDate());
        Glide.with(this)
                .asBitmap()
                .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movie.getPosterPath())
                .into(this.mImageView);

        //


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

    @Override
    public void processMovieReviewsConversionResult(ArrayList<Review> reviews) {
        //mReviewList.clear();
        //mReviewList.addAll(reviews);
        //mreviewAdapter.notifyDataSetChanged();

        LinearLayout ll = (LinearLayout) findViewById(R.id.ll_reviews_wrapper);

        for (Review r : reviews) {
            View v;
            LayoutInflater vi;
            vi = LayoutInflater.from(this);
            v = vi.inflate(R.layout.review_item, null);

            TextView author = (TextView) v.findViewById(R.id.tv_review_item_author);
            TextView content = (TextView) v.findViewById(R.id.tv_review_item_content);

            author.setText(r.getAuthor());
            content.setText(r.getContent());

            ll.addView(v, 0, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            System.out.println(ll.getChildCount());
        }
    }

    @Override
    public void ProcessHTTPResponseBody(String HTTPGETResponse) {
        //TODO: remove all functionality from this method! Only ment for logging RN!!
        //TODO: observe the reponse body and see if placing the review was succesful.
        //TODO: if so,
        //TODO: 1. clear the rating bar with mRatingBar.setRating(0F);
        //TODO: 2. show a toast with success
        // TODO: else, show a toast with failure
        Log.v("{{REPSO}}",HTTPGETResponse);

    }
}
