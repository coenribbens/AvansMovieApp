package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import com.avans.AvansMovieApp.Model.DetailedMovie;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GetYoutubeIdFromMovieIdTest implements MovieIdYoutubeIdConvertable {
    private String youtbeId;
    private CountDownLatch signal;


    //This method is run before every test, the result from "GetYoutubeIdFromMovieId.initializeMovieIdToYoutubeIdRequest()"
    //will use the "processMovieIdDetailedMovieConversionResult(...)" as a callback method.
    @Before
    public void setUp() {
        GetYoutubeIdFromMovieId getDetailedMovieFromMovieId = new GetYoutubeIdFromMovieId(245891, this);
        getDetailedMovieFromMovieId.initializeMovieIdToYoutubeIdRequest();
        signal = new CountDownLatch(1);
    }

    @Override
    public void processMovieIdYoutubeIdConversionResult(String youtubeId) {
        this.youtbeId = youtubeId;
        this.signal.countDown();
    }

    //The "signal.await()" method waits for the signal to be countdown by the callback method before continuing. This needs to be included in every test.
    //This test might fail if phone locale isn't set to english.
    @Test
    public void correctMovieTrailer(){
        try {
            signal.await();
            assertEquals("2AUmvWm5ZDQ", this.youtbeId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}