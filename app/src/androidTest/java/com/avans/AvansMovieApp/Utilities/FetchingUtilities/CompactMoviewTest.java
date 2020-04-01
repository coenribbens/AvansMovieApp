package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import com.avans.AvansMovieApp.Model.CompactMovie;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.Assert;



public class CompactMoviewTest {

    private ArrayList<Integer> genreIdsAttr;
    private CompactMovie compactMovie;


    //Arrange
    @Test
    public void testOrSomething() {
        genreIdsAttr = new ArrayList<Integer>();
        genreIdsAttr.add(1);
        genreIdsAttr.add(2);
        genreIdsAttr.add(3);
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
                genreIdsAttr,
                "titleAttr",
                80,
                "overviewAttr",
                "releaseDateAttr"
        );


        //Act
        Integer popularityAttr = compactMovie.getPopularity();
        Integer voteCountAttr = compactMovie.getVoteCount();
        String posterPathAttr = compactMovie.getPosterPath();
        Integer idAttr = compactMovie.getId();
        String backdropPathAttr = compactMovie.getBackdropPath();
        String origLangAttr = compactMovie.getOriginalLanguage();
        String origTitleAttr = compactMovie.getOriginalTitle();
        String titleAttr = compactMovie.getTitle();
        Integer voteAvgAttr = compactMovie.getVoteAverage();
        String overviewAttr = compactMovie.getOverview();
        String releaseDateAttr = compactMovie.getReleaseDate();


        //Assert
        Assert.assertEquals((long)popularityAttr, 10);
        Assert.assertEquals((long)voteCountAttr, 10);
        Assert.assertEquals(posterPathAttr, true);
        Assert.assertEquals((long)idAttr, 999);
        Assert.assertEquals(backdropPathAttr, "backdropPathAttr");
        Assert.assertEquals(origLangAttr, "origLangAttr");
        Assert.assertEquals(origTitleAttr, "origLangAttr");
        Assert.assertEquals(titleAttr, "origTitleAttr");
        Assert.assertEquals((long)voteAvgAttr, 80);
        Assert.assertEquals(overviewAttr, "overviewAttr");
        Assert.assertEquals(overviewAttr, "overviewAttr");
        Assert.assertEquals(releaseDateAttr, "releaseDateAttr");


    }

}
