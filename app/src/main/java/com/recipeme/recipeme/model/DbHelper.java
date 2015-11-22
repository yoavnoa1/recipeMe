package com.recipeme.recipeme.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.common.collect.Lists;
import com.recipeme.recipeme.entities.Recipe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "RecipeMe.db";
    public static final String TABLE_NAME = "Favorites";

    public DbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL
                (
                        "create table Favorites " +
                                "(" +
                                "recipeId text primary key, " +
                                "insertDate text " +
                                ")"
                );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS Favorites");
        onCreate(db);
    }

    public void insertOrRemoveFavorite(Recipe recipe)
    {
        if (isFavoriteExists(recipe.getId()))
        {
            removeFavorite(recipe);
        }
        else
        {
            insertFavorite(recipe);
        }
    }


    public boolean insertFavorite(Recipe recipe)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("recipeId", recipe.getId());
        contentValues.put("insertDate", Calendar.getInstance().getTime().toString());

        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean removeFavorite(Recipe recipe)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "recipeId=?", new String[]{recipe.getId()});

        return true;
    }

    public boolean isFavoriteExists(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"recipeId"};
        Cursor res = db.query(TABLE_NAME, columns, "recipeId=?", new String[]{id}, null, null, null);
        res.moveToFirst();

        if (!res.isAfterLast())
        {
            res.close();
            return true;
        }

        res.close();
        return false;
    }


    public List<String> getFavoriteRecipes()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"recipeId"};
        Cursor res = db.query(TABLE_NAME, columns, null, null, null, null, null);
        res.moveToFirst();

        ArrayList<String> ids = Lists.newArrayList();

        while(!res.isAfterLast())
        {
            ids.add(res.getString(res.getColumnIndex("recipeId")));
            res.moveToNext();
        }
        res.close();

        return ids;
    }
}
