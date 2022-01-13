package com.sharan.adroidlab.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class sqlitehelper extends SQLiteOpenHelper{

    public sqlitehelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }


    public void insertuserdata(String fname, String lname, String email,String phone,String password){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO user VALUES (NULL, ?, ?, ?,?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,fname);
        statement.bindString(2, lname);
        statement.bindString(3, email);
        statement.bindString(4, phone);
        statement.bindString(5, password);
        statement.bindString(6, "");
        //statement.bindLong(5, password);

        statement.executeInsert();

        insertuserinformation(phone);
    }


    public  void insertuserinformation(String phone)
    {
        Cursor cur;
        cur=getData("select id from user where phone='"+phone+"'");
        cur.moveToFirst();
        int userid= Integer.parseInt(cur.getString(cur.getColumnIndex("Id")));
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO userinformation VALUES (NULL, ?, ?, ?,?,?,?,?,?,?,?,?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindLong(1,userid);
        statement.bindString(2, "");
        statement.bindString(3, "");
        statement.bindLong(4, 0);
        statement.bindString(5, "");
        statement.bindString(6, "");
        statement.bindString(7, "");
        statement.bindString(8, "");
        statement.bindString(9, "");
        statement.bindString(10, "");
        statement.bindString(11, "");
        statement.bindString(12, "");
        statement.bindDouble(13, 0.0);
        //statement.bindLong(5, password);

        statement.executeInsert();
    }


    public boolean phemailcheck(String ph,String email)
    {
        Cursor cur;
        cur=getData("select firstname,lastname,email,phone from user where phone='"+ph+"' or email='"+email+"'");
        if(cur.getCount()>0){
            return  false;
        }
        else
        {
            return true;
        }
    }




    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
