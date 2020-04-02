package com.avans.AvansMovieApp.Datalayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

//THIS CLASS IS EXECUTED WHEN THE APPLICATION IS STARTED
public class MovieDBHandler extends SQLiteOpenHelper {

    private String ON_CREATE_DATABASE = ("CREATE TABLE `userList`" +
            "userId INTEGER(20)" +
            "listId nvarchar(20)" +
            "PRIMARY KEY(`userId`");
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

    public void insertGuestToken(String guestToken) {

        //Log for debugging
        Log.d(TAG, "insertGuestToken: " + guestToken);

        //Get writeable database
        SQLiteDatabase db = this.getWritableDatabase();

        //Insert into the database
        db.insert("userList", guestToken, null);
    }

    public String getGuestToken() {

        //Log for Debugging
        Log.d(TAG, "getGuestToken");

        //Guest the database
        SQLiteDatabase db = this.getWritableDatabase();

        //Query to execute
        String query = "SELECT TOP(1) userId FROM userlist";

        //Cursor to browse through
        Cursor cursor = db.rawQuery(query, null);

        //Token to return
        String guestToken = null;

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

             guestToken = cursor.getString(cursor.getColumnIndex("userId"));
            cursor.close();
        }

        db.close();



        return guestToken;
    }

    public void newMovieList(String userId, String movieListId) {

        //Log for debugging
        Log.d(TAG, "createNewMovieList");

        //Get the database
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("userId", getGuestToken());
        cv.put("listId", movieListId);

        db.insert("userList",null, cv);
    }

    public void deleteMovieList(String userId, String movieListId) {

        //Log for debugging
        Log.d(TAG,"DeleteMovieList");

        //Getting the database
        SQLiteDatabase db = this.getWritableDatabase();

        //Delete the list from the database
        db.execSQL("DELETE FROM userList WHERE listId = " + movieListId);
    }

    public ArrayList<String> getListId() {

        //Log for Debugging
        Log.d(TAG, "getListId");

        //Guest the database
        SQLiteDatabase db = this.getWritableDatabase();

        //Query to execute
        String query = "SELECT TOP(1) listId FROM userlist";

        //Cursor to browse through
        Cursor cursor = db.rawQuery(query, null);

        //Token to return
        ArrayList<String> movieLists = new ArrayList<String>();

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isLast()) {

                movieLists.add(cursor.getString(cursor.getColumnIndex("userId")));

            }
            cursor.close();
        }

        db.close();

        return movieLists;
    }

    public void insertMovieListid(String userId, String listId) {
        //Log for Debugging
        Log.d(TAG, "insertMovieListId");

        //Guest the database
        SQLiteDatabase db = this.getWritableDatabase();

        //Prepare values
    ContentValues values = new ContentValues();
    values.put("userId", userId);
    values.put("listId", listId);

    //Insert into the databse
    db.insert("userList", null, values);

    //Close the database
    db.close();
    }

}
