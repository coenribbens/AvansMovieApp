package com.avans.AvansMovieApp.Model;




import java.util.Locale;
import java.util.UUID;

public class GlobalSettings {


    public static final String apiKey = "b966d45d0ab662f523ce11044a9394ef"; // maybe putting the api key in plaintext in here is a bad idea
    //private String id =  Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    public static String id = UUID.randomUUID().toString();
    public static String language = Locale.getDefault().toLanguageTag(); // in ISO 639-1 format as specified by the API


    public static void setId(String id) {
        GlobalSettings.id = id;
    }

    public static void setLanguage(String language) {
        GlobalSettings.language = language;
    }
}
