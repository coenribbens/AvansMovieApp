package com.avans.AvansMovieApp.Utilities.Miscellaneous;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;

import com.avans.AvansMovieApp.MainActivity;
import com.avans.AvansMovieApp.Model.GlobalVariables;
import com.avans.AvansMovieApp.R;

import java.util.Locale;

public class SwitchLanguagesHelper {

    MainActivity context; // ugh, fucking java things
    Configuration backupConfig;

    public SwitchLanguagesHelper(MainActivity context) {
        this.context = context;
    }
    // currently we just flip the languages. Have to implement another class if we get more languages
    public void flipLanguages() {


        // TODO: add toasts with lang switched to {{lang}}
        if (GlobalVariables.LANG.equals("nl-NL")) {
            this.switchLocale("en-US");
            GlobalVariables.LANG = "en-US";
        } else if (GlobalVariables.LANG.startsWith("en-")) {
            this.switchLocale("nl-NL");
            GlobalVariables.LANG = "nl-NL";
        }
    }

    private void switchLocale(String languageISOStr) {

        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = new Locale(languageISOStr);
        res.updateConfiguration(conf, dm);
        context.onConfigurationChanged(conf);


    }
}
