package com.avans.AvansMovieApp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class Movie implements Parcelable {

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


    public Movie(String originalTitle, String title, ArrayList<String> genreNames, String homepage, String originalLanguage, Integer popularity, String posterPath, ArrayList<String> productionCompaniesNames, Date releaseDate, Integer runTime, String tagLine, String overview) {
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


    //Methods implemented from Parcelable.
    protected Movie(Parcel in) {
        originalTitle = in.readString();
        title = in.readString();
        genreNames = in.createStringArrayList();
        homepage = in.readString();
        originalLanguage = in.readString();
        if (in.readByte() == 0) {
            popularity = null;
        } else {
            popularity = in.readInt();
        }
        posterPath = in.readString();
        productionCompaniesNames = in.createStringArrayList();
        if (in.readByte() == 0) {
            runTime = null;
        } else {
            runTime = in.readInt();
        }
        tagLine = in.readString();
        overview = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(originalTitle);
        dest.writeString(title);
        dest.writeStringList(genreNames);
        dest.writeString(homepage);
        dest.writeString(originalLanguage);
        if (popularity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(popularity);
        }
        dest.writeString(posterPath);
        dest.writeStringList(productionCompaniesNames);
        if (runTime == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(runTime);
        }
        dest.writeString(tagLine);
        dest.writeString(overview);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    //End of methods implemented from Parcelable.

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
