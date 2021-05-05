package com.example.databaseone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper";

    public DBHelper(Context context) {
        super(context, "userData1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table tbl_user " +
                        "(name text primary key, email text, cno text, address text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_user");
        onCreate(db);
    }

    public boolean insertUser (String name, String email, String cno, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("cno", cno);
        contentValues.put("address", address);
        long result= db.insert("tbl_user", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateUser(String name, String email, String cno, String address) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("cno", cno);
        contentValues.put("address", address);
        Cursor cursor = DB.rawQuery("Select * from tbl_user where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("tbl_user", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deleteUser (String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from tbl_user where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("tbl_user", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from tbl_user", null);
        return cursor;

    }

    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT name" + " FROM tbl_user" +
                " WHERE email" + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
//                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
//                " AND " + COL2 + " = '" + oldName + "'";
//        Log.d(TAG, "updateName: query: " + query);
//        Log.d(TAG, "updateName: Setting name to " + newName);
//        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     * @param name
     * @return
     */
    public Boolean deleteName(int id, String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from tbl_user where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("tbl_user", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
//        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
//                + COL1 + " = '" + id + "'" +
//                " AND " + COL2 + " = '" + name + "'";
//        Log.d(TAG, "deleteName: query: " + query);
//        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
//        db.execSQL(query);
    }


//    public Cursor getData(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "SELECT * FROM " + TABLE_NAME;
//        Cursor data = db.rawQuery(query, null);
//        return data;
//    }


}
