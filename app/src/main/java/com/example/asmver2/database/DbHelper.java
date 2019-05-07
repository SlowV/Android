package com.example.asmver2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.asmver2.model.User;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private static String BD_NAME = "asm.db";
    private static int DB_VERSION = 1;

    public DbHelper(Context context) {
        super(context, BD_NAME,null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TblUser.TABLE_NAME + " (" +
                TblUser.ID + " integer primary key autoincrement," +
                TblUser.USERNAME + " text," +
                TblUser.GENDER + " text," +
                TblUser.DESCRIPTION + " text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TblUser.USERNAME, user.getUsername());
        values.put(TblUser.GENDER, user.getGender());
        values.put(TblUser.DESCRIPTION, user.getDescription());
        long result = db.insert(TblUser.TABLE_NAME, null, values);
        return result > 0;
    }

    public List<User> getListUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<User> listUser = new ArrayList<>();
        String sql = "select * from " + TblUser.TABLE_NAME;
        Cursor c = db.rawQuery(sql, null);

        while (c.moveToNext()){
            User user = new User(c.getInt( c.getColumnIndex(TblUser.ID)),
                    c.getString(c.getColumnIndex(TblUser.USERNAME)),
                    c.getString(c.getColumnIndex(TblUser.GENDER)),
                    c.getString(c.getColumnIndex(TblUser.DESCRIPTION)));
            listUser.add(user);
        }
        return listUser;
    }

    public long updateUser(User user, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TblUser.USERNAME, user.getUsername());
        values.put(TblUser.GENDER, user.getGender());
        values.put(TblUser.DESCRIPTION, user.getDescription());
        return (long) db.update(TblUser.TABLE_NAME, values, TblUser.ID + " = " + id, null);
    }

    public long deleteUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return (long) db.delete(TblUser.TABLE_NAME, TblUser.ID + " = " + id, null);
    }
}
