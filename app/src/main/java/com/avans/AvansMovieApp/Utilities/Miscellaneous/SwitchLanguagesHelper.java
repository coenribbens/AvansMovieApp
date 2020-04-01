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
    Configuration backupConfig; // lol, cant even fucking finalize this. Java is such a fucking joke

    public SwitchLanguagesHelper(MainActivity context) {
        this.context = context;
    }
    // currently we just flip the languages. Have to implement another class if we get more languages
    public void flipLangages() {


        // TODO: add toasts with lang switched to {{lang}}
        if (GlobalVariables.LANG.equals("nl-NL")) {
            this.switchLocale("en-US");
            GlobalVariables.LANG = "en-US";
        } else if (GlobalVariables.LANG.startsWith("en-")) {
            this.switchLocale("nl-NL");
            GlobalVariables.LANG = "nl-NL";
        }
    }


    // this got way messier than it should have. Fuck java.
    private void switchLocale(String languageISOStr) {
<<<<<<< HEAD


        
        if(context.getLangSwitched() == 0){
            // backup the first conf
            Resources res = context.getResources();
            Configuration conf = res.getConfiguration();
            this.backupConfig = conf;
        }


        if(!(context.getLangSwitchedBool()) || context.getLangSwitched() > 0){
            // if false we're switched and should restore
            Resources res = context.getResources();
            Configuration conf = this.backupConfig;
            conf.locale = new Locale(languageISOStr);
            res.updateConfiguration(conf, res.getDisplayMetrics());

        }
        else if(context.getLangSwitchedBool()){
            // create new res
            Resources res = context.getResources();
            Configuration conf = res.getConfiguration();
            conf.locale = new Locale(languageISOStr);
            res.updateConfiguration(conf, res.getDisplayMetrics());
        }










=======
        Resources res = context.getResources();
        Configuration conf = res.getConfiguration();
        conf.locale = new Locale(languageISOStr);
        Log.v("LANG",languageISOStr);
        res.updateConfiguration(conf, res.getDisplayMetrics());
>>>>>>> parent of 32e13c0... Update SwitchLanguagesHelper.java
    }
}
