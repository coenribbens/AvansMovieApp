package com.avans.AvansMovieApp.Model;




import java.util.Locale;
import java.util.UUID;

public class GlobalSettings {


    public static final String apiKeyV3 = "b966d45d0ab662f523ce11044a9394ef"; // maybe putting the api key in plaintext in here is a bad idea
    public static final String apiKeyV4 = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiOTY2ZDQ1ZDBhYjY2MmY1MjNjZTExMDQ0YTkzOTRlZiIsInN1YiI6IjVlNzM1YTMyY2FiZmU0MDAxMTFjMTFmMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.T24irmS_96EdKafis4i9hGzqpmeLHS4jlNa-DzNioXg";

    //private String id =  Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    public static String deviceId = UUID.randomUUID().toString(); // make this persistent
    public static String language = Locale.getDefault().toLanguageTag(); // in ISO 639-1 format as specified by the API

    public static String sessionToken;


    public static void setSessionToken(String sessionToken) {
        GlobalSettings.sessionToken = sessionToken;
    }

    public static void setdeviceId(String deviceId) {
        GlobalSettings.deviceId = deviceId;
    }

    public static void setLanguage(String language) {
        GlobalSettings.language = language;
    }
}
