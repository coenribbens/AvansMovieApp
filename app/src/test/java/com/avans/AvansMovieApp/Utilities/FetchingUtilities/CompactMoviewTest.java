package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import com.avans.AvansMovieApp.Model.CompactMovie;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.Assert;



public class CompactMoviewTest {

    private ArrayList<Integer> genreIds;
    private CompactMovie compactMovie;


    //Arrange
    @Test
    public void testOrSomething() {
        genreIds = new ArrayList<Integer>();
        genreIds.add(1);
        genreIds.add(2);
        genreIds.add(3);
        this.compactMovie = new CompactMovie(
                10,
                10,
                true,
                "posterPathAttr",
                999,
                true,
                "backdropPathAttr",
                "origLangAttr",
                "origTitleAttr",
                genreIds,
                "titleAttr",
                80,
                "overviewAttr",
                "releaseDateAttr"
        );


        //Act
        Integer popularityAttr = compactMovie.getPopularity();
        Integer voteCountAttr = compactMovie.getVoteCount();
        Boolean videoAttr = compactMovie.isVideo();
        String posterPathAttr = compactMovie.getPosterPath();
        Integer idAttr = compactMovie.getId();
        String backdropPathAttr = compactMovie.getBackdropPath();
        String origLangAttr = compactMovie.getOriginalLanguage();
        String origTitleAttr = compactMovie.getOriginalTitle();
        ArrayList<Integer> genreIdsAttr = compactMovie.getGenreIds();
        String titleAttr = compactMovie.getTitle();
        Integer voteAvgAttr = compactMovie.getVoteAverage();
        String overviewAttr = compactMovie.getOverview();
        String releaseDateAttr = compactMovie.getReleaseDate();


        //Assert
        Assert.assertEquals((long)popularityAttr, 10);
        Assert.assertEquals((long)voteCountAttr, 10);
        Assert.assertTrue(videoAttr);
        Assert.assertEquals(posterPathAttr, "posterPathAttr");
        Assert.assertEquals((long)idAttr, 999);
        Assert.assertEquals(backdropPathAttr, "backdropPathAttr");
        Assert.assertEquals(origLangAttr, "origLangAttr");
        Assert.assertEquals(origTitleAttr, "origTitleAttr");
        Assert.assertNotNull(genreIdsAttr);
        Assert.assertEquals(titleAttr, "titleAttr");
        Assert.assertEquals((long)voteAvgAttr, 80);
        Assert.assertEquals(overviewAttr, "overviewAttr");
        Assert.assertEquals(overviewAttr, "overviewAttr");
        Assert.assertEquals(releaseDateAttr, "releaseDateAttr");


    }

}
