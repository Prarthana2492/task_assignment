package com.example.task_assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
/**
 * Created by Prarthana on 25,April,2020
 */
public class UserDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Userform.db";
    public static final String TABLE_NAME = "user_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "PASSWORD";
   static UserDB userDBInstance;

//Singleton UserDB
    private UserDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    //Return DB Instance
    public static UserDB getUserDB(Context context){
        if (userDBInstance==null){
            userDBInstance=new UserDB(context);
        }
        return userDBInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT,PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    //Insert Data
    public boolean insertdata(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }
    //Checking Login
    public boolean checkUser(String email, String password) {
        String[] columns = {COL_1};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COL_3 + " = ?" + " AND " + COL_4 + " = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;

    }
    //Checking Email exist
    public boolean isEmailExist(String email) {
        String[] columns = {COL_1};
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COL_3 + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
    //Get Password
    public Cursor getPassword(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select " + COL_4 + " from " + TABLE_NAME + " where " + COL_3+ " = ?" ,  new String[]{email});
        return res;
    }
}