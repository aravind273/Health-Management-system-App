package com.example.health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class RegisterPatientDatabase extends SQLiteOpenHelper {
    public static final String DB_TABLE="register_patient";




    public RegisterPatientDatabase(@Nullable Context context) {
        super(context,"RegisterPatient.db", null, 500);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table register_patient(name text,age text,email text primary key,password text)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists register_patient");

    }
    public boolean insertdata(String name,String age,String email,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name" ,name);
        contentValues.put("age",age);
        contentValues.put("email",email);
        contentValues.put("password",password);


        long res=db.insert(DB_TABLE,null,contentValues);
        if(res== -1)
        {
            return  false;
        }
        else {
            return true;
        }
    }
    public boolean checkEmail(String email)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+DB_TABLE+" where email=?",new String[]{email});
        if(res.getCount()>0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean checkEmailAndPassword(String email,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+DB_TABLE+" where email=? and password=?",new String[]{email,password});
        if(res.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Cursor retrivedataofperson(String email, String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+DB_TABLE+" where email=? and password=?",new String[]{email,password},null);
        return res;
    }
}
