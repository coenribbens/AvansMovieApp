package com.avans.AvansMovieApp.Model;

public class ListMovie {
    private int id;
    private String listName;
    private String description;
    private String posterPath;
    private int itemCount;
    private String language;

    public ListMovie(int id, String listName, String description, String posterPath, int itemCount, String language) {
        this.id = id;
        this.listName = listName;
        this.description = description;
        this.posterPath = posterPath;
        this.itemCount = itemCount;
        this.language = language;
    }

    public ListMovie() {

    }
}
