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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
