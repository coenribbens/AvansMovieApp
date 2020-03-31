package com.avans.AvansMovieApp.Datalayer;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//THIS CLASS IS EXECUTED WHEN THE APPLICATION IS STARTED
public class MovieDBHandler extends SQLiteOpenHelper {

    private String ON_CREATE_DATABASE = ("CREATE TABLE `userList`" +
            "userid INTEGER(20)" +
            "listId nvarchar(20)" +
            "PRIMARY KEY(`userid`");
    private String ON_UPDATE_DATABASE = ("DROP TABLE IF EXISTS `userList`");
    private String TAG = this.getClass().getSimpleName();

    //Constructor for the class
    public MovieDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        //Debug log
        Log.d(TAG, "onCreate " + db);

        //Excute Oncreate Query
        db.execSQL(ON_CREATE_DATABASE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Debug log
        Log.d(TAG, "Old version: " + oldVersion + " " + "New Version: " + newVersion);

        //Execute db statement
        db.execSQL(ON_UPDATE_DATABASE);

        //Create the table anew
        db.execSQL(ON_CREATE_DATABASE);
    }
}
