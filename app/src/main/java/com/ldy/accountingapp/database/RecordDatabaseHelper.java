package com.ldy.accountingapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ldy.accountingapp.RecordBean;
import com.ldy.accountingapp.database.RecordDatabaseSchema.RecordTable;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ldy on 19/1/5.
 */

public class RecordDatabaseHelper extends SQLiteOpenHelper {

    private static String TAG = "RecordDatabaseHelper";

    private static final  String DB_NAME = "record";

//    private SQLiteDatabase mDatabase = getWritableDatabase();

    //创建数据库SQL语句
    private static final  String CREATE_RECORD_DB = "create table "+RecordDatabaseHelper.DB_NAME+"("
            + "id integer primary key autoincrement"
            +"uuid text,"
            +"type integer,"
            +"category text,"
            +"remark text,"
            +"amount double,"
            +"time integer,"
            +"date date";


    public RecordDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RECORD_DB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addRecord(RecordBean bean){
        SQLiteDatabase db = getWritableDatabase();
        db.insert(RecordTable.NAME,null,getContentValues(bean));
    }

    public void removeRecord(String uuid){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(RecordTable.NAME,"uuid = ?",new String[]{uuid});
    }

    public void updateRecord(RecordBean bean){
        SQLiteDatabase db = getWritableDatabase();
        String uuid = bean.getUuid();

        //way 1
        ContentValues values = getContentValues(bean);
        db.update(RecordTable.NAME,values,"uuid = ?",new String[]{uuid});

        //way 2
//        removeRecord(uuid);
//        addRecord(bean);
    }

    public LinkedList<RecordBean> queryRecord(String dateStr){

        LinkedList<RecordBean> records = new LinkedList<>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select DISTINCE * from record where date = ? order by time asc",
                new String[]{dateStr});

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String uuid = cursor.getString(cursor.getColumnIndex("uuid"));
            int type = cursor.getInt(cursor.getColumnIndex("int"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            String remark = cursor.getString(cursor.getColumnIndex("remark"));
            double amount = cursor.getDouble(cursor.getColumnIndex("amount"));
            long time = cursor.getLong(cursor.getColumnIndex("time"));
            String date = cursor.getString(cursor.getColumnIndex("date"));

            RecordBean record = new RecordBean();
            record.setUuid(uuid);
            record.setType(type);
            record.setCategory(category);
            record.setAmount(amount);
            record.setRemark(remark);
            record.setTimeStamp(time);
            record.setDate(date);

            records.add(record);

            cursor.moveToNext();
        }
        cursor.close();


        return records;

    }


    public LinkedList<String> getAvaiableDate(){

        LinkedList <String> dates = new LinkedList<>();

        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("select DISTINCE * from record order by time asc",
                new String[]{});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String date = cursor.getString(cursor.getColumnIndex("data"));

            if(!dates.contains(date)){
                dates.add(date);
            }

            cursor.moveToNext();
        }
        cursor.close();

        return dates;
    }


    private static ContentValues getContentValues(RecordBean bean){
        ContentValues values = new ContentValues();
        values.put(RecordTable.cols.UUID,bean.getUuid());
        values.put(RecordTable.cols.TYPE,bean.getType());
        values.put(RecordTable.cols.CATEGORY,bean.getCategory());
        values.put(RecordTable.cols.REMARK,bean.getRemark());
        values.put(RecordTable.cols.AMOUNT,bean.getAmount());
        values.put(RecordTable.cols.TIME,bean.getTimeStamp());
        values.put(RecordTable.cols.DATE,bean.getDate());
        return values;
    }




}
