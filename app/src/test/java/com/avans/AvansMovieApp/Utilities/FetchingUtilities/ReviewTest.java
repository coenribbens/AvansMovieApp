package com.avans.AvansMovieApp.Utilities.FetchingUtilities;

import com.avans.AvansMovieApp.Model.CompactMovie;
import com.avans.AvansMovieApp.Model.Review;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class ReviewTest {

    private Review review;


    //Arrange
    @Test
    public void testOrSomething() {
        this.review = new Review(
          "AuthorAttr",
          "ContentAttr"
        );

        //Act
        String authorAttr = review.getAuthor();
        String contentAttr = review.getContent();

        //Assert
        Assert.assertEquals(authorAttr, "AuthorAttr");
        Assert.assertEquals(contentAttr, "ContentAttr");


    }

}
