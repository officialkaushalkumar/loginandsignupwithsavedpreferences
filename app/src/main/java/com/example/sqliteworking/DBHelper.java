package com.example.sqliteworking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context,"login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("create table logintable(email text primary key,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists logintable");
    }

    public boolean insertdata(String email,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = db.insert("logintable",null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean checkemail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from logintable where email = ?",new String[] {email});
        if(cursor.getCount()>0){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean checkemailandpass(String email,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from logintable where email =? and password=?",new String[] {email,password});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }
}
