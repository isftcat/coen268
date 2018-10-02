package com.example.a.woofui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 5/5/2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String TAG = DbHelper.class.getSimpleName();
    public static final String DB_NAME = "woof.db";
    public static final int DB_VERSION = 1;

    public static final String OWNER_DETAILS = "users";
    public static final String OWNER_ID = "owner_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "password";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ZIP = "zip";
    /*
    create table users(
        id integer primary key autoincrement,
        email text,
        password text);
     */
    public static final String CREATE_TABLE_OWNER_DETAILS = "CREATE TABLE " + OWNER_DETAILS + "("
            + OWNER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +  COLUMN_NAME + " TEXT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PASS + " TEXT);"
            + COLUMN_ZIP+" TEXT);";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_OWNER_DETAILS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + OWNER_DETAILS);
        onCreate(db);
    }

    /**
     * Storing user details in database
     * */
    public void addUser(String email, String password, String name, String zip) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_ZIP,zip);
        values.put(COLUMN_PASS, password);

        long id = db.insert(OWNER_DETAILS, null, values);
        db.close();

        Log.d(TAG, "user inserted" + id);
    }

    public boolean getUser(String email, String pass, String name, String Name, String zip){
        //HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "select * from  " + OWNER_DETAILS + " where " +
                COLUMN_EMAIL + " = " + "'"+email+"'" + " and " + COLUMN_PASS + " = " + "'"+pass+"'" + " and " + COLUMN_NAME + " = " + "'"+name+"'" + " and " + COLUMN_ZIP + " = " + "'"+zip+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {

            return true;
        }
        cursor.close();
        db.close();

        return false;
    }
}
