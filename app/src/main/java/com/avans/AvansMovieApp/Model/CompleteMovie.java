package com.avans.AvansMovieApp.Model;

import java.util.ArrayList;
import java.util.Date;

public class CompleteMovie {

    private String originalTitle;
    private String title;
    private ArrayList<String> genreNames;
    private String homepage;
    private String originalLanguage;
    private Integer popularity;
    private String posterPath;
    private ArrayList<String> productionCompaniesNames;
    private Date releaseDate;
    private Integer runTime;
    private String tagLine;
    private String overview;


    public CompleteMovie(String originalTitle, String title, ArrayList<String> genreNames, String homepage, String originalLanguage, Integer popularity, String posterPath, ArrayList<String> productionCompaniesNames, Date releaseDate, Integer runTime, String tagLine, String overview) {
        this.originalTitle = originalTitle;
        this.title = title;
        this.genreNames = genreNames;
        this.homepage = homepage;
        this.originalLanguage = originalLanguage;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.productionCompaniesNames = productionCompaniesNames;
        this.releaseDate = releaseDate;
        this.runTime = runTime;
        this.tagLine = tagLine;
        this.overview = overview;
    }


    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getGenreNames() {
        return genreNames;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public ArrayList<String> getProductionCompaniesNames() {
        return productionCompaniesNames;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Integer getRunTime() {
        return runTime;
    }

    public String getTagLine() {
        return tagLine;
    }

    public String getOverview() {
        return overview;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenreNames(ArrayList<String> genreNames) {
        this.genreNames = genreNames;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setProductionCompaniesNames(ArrayList<String> productionCompaniesNames) {
        this.productionCompaniesNames = productionCompaniesNames;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
