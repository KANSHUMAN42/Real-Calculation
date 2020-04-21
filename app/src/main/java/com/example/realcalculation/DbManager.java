package com.example.realcalculation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbManager extends SQLiteOpenHelper {
    public static final String TAG="hey";
    public static final  String Tablename="STUDENT_TABLE";
    public static final  String Databasename="Student.db";
    // public static final String Column_1="ID";
    public static final  String Column_2="NAME";
    public static final  String Column_3="SURNAME";
    public static final  String Column_4="MARKS";
    SQLiteDatabase db;
    public DbManager( Context context) {

        super(context, Databasename , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry="CREATE TABLE "+Tablename+"(NAME String PRIMARY KEY ,SURNAME TEXT)";
        db.execSQL(qry);
        //  Log.i(TAG,"table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+Tablename);
    }

    public boolean addRecord(String p1, String p2) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column_2,p1);
        contentValues.put(Column_3,p2);
        long s= db.insert(Tablename, null, contentValues);
        if(s == -1){
            return false;
        }
        else
            return true;
    }

    public Cursor fetchAll(){
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor cursor =db.rawQuery( " select* from "+Tablename ,null);
        return cursor;


    }


}
