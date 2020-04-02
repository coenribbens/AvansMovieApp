package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import com.avans.AvansMovieApp.Model.DetailedMovie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class GetDetailedMovieFromMovieIdTest implements MovieIdDetailedMovieConvertable {
    private DetailedMovie detailedMovie;
    private CountDownLatch signal;

    //This method is run before every test, the result from "getDetailedMovieFromMovieId.initializeMovieIdToDetailedMovieRequest()"
    //will use the "processMovieIdDetailedMovieConversionResult(...)" as a callback method.
    @Before
    public void setUp() throws Exception {
        GetDetailedMovieFromMovieId getDetailedMovieFromMovieId = new GetDetailedMovieFromMovieId(680, this);
        getDetailedMovieFromMovieId.initializeMovieIdToDetailedMovieRequest();
        signal = new CountDownLatch(1);
    }

    @Override
    public void processMovieIdDetailedMovieConversionResult(DetailedMovie detailedMovie) {
        this.detailedMovie = detailedMovie;
        this.signal.countDown();
    }

    //The "signal.await()" method waits for the signal to be countdown by the callback method before continuing. This needs to be included in every test.
    @Test
    public void correctMovie(){
        try {
            signal.await();
            assertEquals("Pulp Fiction", this.detailedMovie.getOriginalTitle());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void genreNamesIsNotNull(){
        try{
            signal.await();
            assertNotNull(this.detailedMovie.getGenreNames());
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Test
    public void productionCompaniesNamesIsNotNull(){
        try {
            signal.await();
            assertNotNull(this.detailedMovie.getProductionCompaniesNames());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}