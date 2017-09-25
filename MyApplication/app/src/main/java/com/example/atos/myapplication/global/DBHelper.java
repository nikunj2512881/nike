package com.example.atos.myapplication.global;

/**
 * Created by Atos on 02/08/17.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.atos.myapplication.model.User;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    ArrayList<String> columnNames = new ArrayList<>();

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
    public void setTableHeaders(String createQuery) {

        String result[] = (createQuery.substring(createQuery.indexOf("(") + 1, createQuery.indexOf(")"))).split(",");
        for (int i=0,j=0;i<result.length;i++){
            String[]  tokens = result[i].split(" ");
            for(j=0;tokens[j].length() == 0;j++);
            columnNames.add(tokens[j]);
            Log.d("tag","col : "+columnNames.get(i));
        }
    }
    public boolean insertRecord (User user,String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int i=1;
        contentValues.put(columnNames.get(i++),user.getUserName());
        contentValues.put(columnNames.get(i++),user.getUserId());
        contentValues.put(columnNames.get(i++),user.getUserPassword());
        db.insert(tableName, null, contentValues);
        return true;
    }

    public Cursor getRecordFromTable(int id,String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+tableName+" where id="+id+"", null );
        return res;
    }

    public int getNumberOfRows(String tableName){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, tableName);
        return numRows;
    }

    public boolean updateRecord (User user,String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int i=0;
        contentValues.put(columnNames.get(i++),user.getId());
        contentValues.put(columnNames.get(i++),user.getUserName());
        contentValues.put(columnNames.get(i++),user.getUserId());
        contentValues.put(columnNames.get(i++),user.getUserPassword());
        db.update(tableName, contentValues, columnNames.get(0)+" = ? ", new String[] { Integer.toString(user.getId()) } );
        return true;
    }

    public Integer deleteRecordbyId (Integer id,String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tableName,
                columnNames.get(0)+" = ? ",
                new String[] { Integer.toString(id) });
    }
    public Boolean deleteAllRecords(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + tableName;
        db.execSQL(SQL_DELETE_ENTRIES);
        return true;
    }
    public ArrayList<User> getAllRecordsFromTable(String tableName) {
        ArrayList<User> array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+tableName, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            int id = Integer.parseInt(res.getString(res.getColumnIndex(columnNames.get(0))));
            String userName = res.getString(res.getColumnIndex(columnNames.get(1)));
            String userId = res.getString(res.getColumnIndex(columnNames.get(2)));
            String password = res.getString(res.getColumnIndex(columnNames.get(3)));
            User user = new User(id,userName,userId,password);
            array_list.add(user);
            res.moveToNext();
        }
        return array_list;
    }

    public void createTableWithQueryAndTableName(String createQuery,String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
         db.execSQL(createQuery);
         setTableHeaders(createQuery);
    }
}