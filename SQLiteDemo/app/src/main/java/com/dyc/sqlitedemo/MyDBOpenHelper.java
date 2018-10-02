package com.dyc.sqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by apple on 2018/2/18.
 */

public class MyDBOpenHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "myDB.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MyContract.MyEntry.TABLE_NAME + " ("+
                    MyContract.MyEntry._ID + " INTEGER PRIMARY KEY NOT NULL," +
                    MyContract.MyEntry.COLUMN_NAME_NAME + " TEXT," +
                    MyContract.MyEntry.COLUMN_NAME_EMAIL + " TEXT,"+
                    MyContract.MyEntry.COLUMN_NAME_FAVORITE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS" + MyContract.MyEntry.TABLE_NAME;


    public MyDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion){
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
    }
}




