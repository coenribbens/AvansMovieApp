package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import com.avans.AvansMovieApp.Model.CompactMovie;
import com.avans.AvansMovieApp.Model.DetailedMovie;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class DetailedMoviewTest {

    private DetailedMovie detailedMovie;
    private ArrayList<String> genreNames = new ArrayList<String>();
    private ArrayList<String> companyNames = new ArrayList<String>();


    //Arrange
    @Test
    public void testOrSomething() {

        genreNames.add("genreNamesAttr1");
        genreNames.add("genreNamesAttr2");
        genreNames.add("genreNamesAttr3");

        companyNames.add("companyNamesAttr1");
        companyNames.add("companyNamesAttr2");
        companyNames.add("companyNamesAttr3");


        this.detailedMovie = new DetailedMovie(
                "originalTitleAttr",
                "titleAttr",
                genreNames,
                "homepageAttr",
                "origLangAttr",
                99,
                "posterPathAttr",
                companyNames,
                "releaseDataAttr",
                999,
                "tagLineAttr",
                "overviewAttr"
        );


        //Act
        String originalTitleAttr = detailedMovie.getOriginalTitle();
        String titleAttr = detailedMovie.getTitle();
        ArrayList<String > genreNamesAttr = detailedMovie.getGenreNames();
        String homepageAttr = detailedMovie.getHomepage();
        String originalLangAttr = detailedMovie.getOriginalLanguage();
        Integer popularityAttr = detailedMovie.getPopularity();
        String posterPathAttr = detailedMovie.getPosterPath();
        ArrayList<String> companyNamesAttr = detailedMovie.getProductionCompaniesNames();
        String releaseDateAttr = detailedMovie.getReleaseDate();
        Integer runTimeAttr = detailedMovie.getRunTime();
        String tagLineAttr = detailedMovie.getTagLine();
        String overviewAttr = detailedMovie.getOverview();



        //Assert
        Assert.assertEquals(originalTitleAttr,"originalTitleAttr");
        Assert.assertEquals(titleAttr,"titleAttr" );
        Assert.assertNotNull(genreNamesAttr);
        Assert.assertEquals(homepageAttr,"homepageAttr");
        Assert.assertEquals(originalLangAttr, "origLangAttr");
        Assert.assertEquals((long)popularityAttr, 99);
        Assert.assertEquals(posterPathAttr,"posterPathAttr");
        Assert.assertNotNull(companyNamesAttr);
        Assert.assertEquals(releaseDateAttr,"releaseDataAttr" );
        Assert.assertEquals( (long)runTimeAttr,999);
        Assert.assertEquals(tagLineAttr,"tagLineAttr");
        Assert.assertEquals(overviewAttr,"overviewAttr");





    }

}
