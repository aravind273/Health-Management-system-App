package com.example.health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BookAppointmentDatabase extends SQLiteOpenHelper {
    public  static final String DB_NAME="BOOK_APPOINTMENT.db";
    public static final String DB_TABLE="BOOK_APPOINTMENT_TABLE";
    public  static final String COL_1="Name";
    public static final String COL_2="Doctor";
    public static final String COL_3="Age";
    public static final String COL_4="Sex";
    public static final String COL_5="Date";
    public static final String COL_6="Time";

    public BookAppointmentDatabase(@Nullable Context context) {
        super(context,DB_NAME, null,70);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+DB_TABLE+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,DOCTOR TEXT,AGE TEXT,SEX TEXT,DATE TEXT,TIME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists "+DB_TABLE);
        onCreate(db);
    }
    public boolean insertdata(String name,String doctor,String age,String sex,String date,String time)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,doctor);
        contentValues.put(COL_3,age);
        contentValues.put(COL_4,sex);
        contentValues.put(COL_5,date);
        contentValues.put(COL_6,time);

        long res=db.insert(DB_TABLE,null,contentValues);
        if(res== -1)
        {
            return  false;
        }
        else {
            return true;
        }
    }
    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+DB_TABLE,null);
        return res;
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(DB_TABLE,"Id = ?",new String[]{ id });
    }




}
