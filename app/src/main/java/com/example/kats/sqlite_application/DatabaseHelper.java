package com.example.kats.sqlite_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kats on 3/26/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String Database_Name = "student.db" ;
    public static final String Table_Name = "student_table" ;
    public static final String Col_1 = "ID" ;
    public static final String Col_2 = "NAME" ;
    public static final String Col_3 = "SURNAME" ;
    public static final String Col_4 = "MARKS" ;

    //whenever this constructor will be called , the database will be created

    public DatabaseHelper(Context context) {
        super(context, Database_Name, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase(); //just to check if created or not
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + Table_Name +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER) ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " +Table_Name);
        onCreate(db);

    }

    public boolean insertData(String name , String surname , String marks)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Col_2 , name);
        contentValues.put(Col_3 , surname);
        contentValues.put(Col_4 , marks);

        long result = db.insert(Table_Name , null , contentValues);

        if(result == -1)
            return false;

        else
            return true;

    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from " + Table_Name , null);
        return res;
    }

    public boolean updateData(String id , String name , String surname , String marks) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Col_1 , id);
        cv.put(Col_2 , name);
        cv.put(Col_3 , surname);
        cv.put(Col_4 , marks);

        db.update(Table_Name , cv , "ID = ?" , new String[] { id });

        return true;
    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(Table_Name , "ID = ?" , new String[] { id });
    }
}