package com.test.new2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


public class databasehelper extends SQLiteOpenHelper
{
    public static final String db_nm="blast_popper.db";
    public static final String tb_nm="highscoretb";
    public static final String tb1_nm="tb1";
    public static final String col1="id";
    public static final String col2="highscore";
    SQLiteDatabase db=this.getWritableDatabase();

    public databasehelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, db_nm, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+tb_nm+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,HIGHSCORE INTEGER)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+tb_nm);
        onCreate(db);
    }

    public boolean insertdata(int highscore)
    {

        ContentValues cv=new ContentValues();
        cv.put(col2, highscore);

        long a=db.insert(tb_nm, null, cv);
        if(a==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Cursor showdata()
    {
        Cursor res=db.rawQuery("select MAX(highscore) as highscore from highscoretb", null);
        return res;

    }


}
