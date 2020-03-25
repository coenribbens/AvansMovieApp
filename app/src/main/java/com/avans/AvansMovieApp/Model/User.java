package com.avans.AvansMovieApp.Model;

import java.util.Locale;
import java.util.UUID;

public class User {

    private String id = UUID.randomUUID().toString();
    private String language = Locale.getDefault().toLanguageTag(); // in ISO 639-1 format as specified by the API




}
