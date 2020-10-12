package com.ahiralabata.ahirasqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import java.util.ArrayList;

import androidx.annotation.Nullable;

class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "ahira_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_BIO = "biodata";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "name";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_BIO + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_FIRSTNAME + " TEXT);";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("table", CREATE_TABLE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_BIO + "'");
        onCreate(db);
    }

    public long addName(String nama){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, nama);
        long insert = db.insert(TABLE_BIO, null, values);
        return insert;
    }

    public ArrayList<String> getAllNames(){
        ArrayList<String> nameArray = new ArrayList<String>();
        String name = "";
        String query = "SELECT * FROM "+ TABLE_BIO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            do {
                name = c.getString(c.getColumnIndex(KEY_FIRSTNAME));
                nameArray.add(name);
            } while (c.moveToNext());
            Log.d("array", nameArray.toString());
        }
        return nameArray;
    }
}
