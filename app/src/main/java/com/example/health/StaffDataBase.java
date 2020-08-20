package com.example.health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StaffDataBase extends SQLiteOpenHelper {
    public  static final String DB_NAME="staff.db";
    public static final String DB_TABLE="STAFF_TABLE";
    public  static final String COL_1="Id";
    public  static final String COL_2="Name";
    public static final String COL_3="Age";
    public static final String COL_4="Address";
    public static final String COL_5="Phonenumber";
    public static final String COL_6="Category";

    public StaffDataBase(@Nullable Context context) {
        super(context,DB_TABLE, null, 55);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+DB_TABLE+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,AGE TEXT,ADDRESS TEXT,PHONENUMBER TEXT,CATEGORY TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists "+DB_TABLE);
        onCreate(db);
    }
    public boolean insertdata(String id,String name,String age,String address,String phone,String category)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,age);
        contentValues.put(COL_4,address);
        contentValues.put(COL_5,phone);
        contentValues.put(COL_6,category);

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
    public boolean Update(String id,String name,String age,String address,String phone,String category)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,age);
        contentValues.put(COL_4,address);
        contentValues.put(COL_5,phone);
        contentValues.put(COL_6,category);
        db.update(DB_TABLE,contentValues,"Id = ?",new String[] { id });
        return true;
    }
    public Integer deleteData(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(DB_TABLE,"Id = ?",new String[]{ id });
    }




}
