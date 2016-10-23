package com.revittechnology.mullahnasruddin.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.revittechnology.mullahnasruddin.R;
import com.revittechnology.mullahnasruddin.model.Joke;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private final Context context;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public DatabaseManager(Context ctx) {
        this.context = ctx;
        dbHelper = new DatabaseHelper(context, getDB_NAME());
        db = dbHelper.openDataBase();
    }

    public ArrayList<Joke> getAllRow(String category) {
        ArrayList<Joke> jokeList = new ArrayList<Joke>();
        Cursor cur;
        try {
            cur = db.rawQuery("SELECT * FROM " + getTABLE1_NAME() + " WHERE " + getCOL4_CAT() + " = '" + category + "'", null);

            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    Joke j = new Joke();
                    j.setId(cur.getInt(0));
                    j.setTitle(cur.getString(1));
                    j.setJoke(cur.getString(2));
                    j.setCat(cur.getString(3));
                    j.setFavorites(cur.getInt(4));
                    jokeList.add(j);
                } while (cur.moveToNext());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DB ERROR", e.toString());
        }
        return jokeList;
    }

    public void updateFavorites(Joke joke) {
        String updateQuery = "UPDATE " + getTABLE1_NAME() + " SET fav='" + joke.getFavorites() + "' WHERE id=" + joke.getId();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL(updateQuery);
        db.close();
    }

    public List<Joke> getFavoritesRow() {
        List<Joke> jokeList = new ArrayList<Joke>();
        Cursor cur;
        try {
            cur = db.rawQuery("SELECT * FROM " + getTABLE1_NAME() + " WHERE " + getCOL5_FAV() + "='1'", null);
            cur.moveToFirst();
            if (!cur.isAfterLast()) {
                do {
                    Joke j = new Joke();
                    j.setId(cur.getInt(0));
                    j.setTitle(cur.getString(1));
                    j.setJoke(cur.getString(2));
                    j.setCat(cur.getString(3));
                    j.setFavorites(cur.getInt(4));
                    jokeList.add(j);
                } while (cur.moveToNext());
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.e("DB ERROR", e.toString());
        }
        return jokeList;
    }


    public String getCOL1_ID() {
        return (context.getResources().getString(R.string.col_id));
    }

    public String getCOL2_TITLE() {
        return (context.getResources().getString(R.string.col_title));
    }

    public String getCOL3_JOKE() {
        return (context.getResources().getString(R.string.col_joke));
    }


    public String getCOL4_CAT() {
        return (context.getResources().getString(R.string.col_cat));
    }


    public String getCOL5_FAV() {
        return (context.getResources().getString(R.string.col_fav));
    }


    public String getDB_NAME() {
        return (context.getResources().getString(R.string.db_name));
    }


    public String getTABLE1_NAME() {
        return (context.getResources().getString(R.string.table_name));
    }

}
