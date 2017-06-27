package com.example.k.diadiembinhphuoc.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by may38 on 5/30/2017.
 */

public class SQLiteDataAccessHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "DLBinhPhuoc.sqlite";
    public static final String DBLOCATION = "/data/data/com.example.k.diadiembinhphuoc/databases/";//Nơi chứa CSDL trong điện thoại

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private SQLiteDataAccessHelper sqLiteDataAccessHelper;

    public SQLiteDataAccessHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    //truy vấn không trả kết quà: CREATE, INSERT, UPDATE, DELETE...
    public void queryData(String sql) {
        openDatabase();
        mDatabase.execSQL(sql);
    }

    //truy vấn có trả kết qua: SELECT.
    public Cursor getData(String sql) {
        openDatabase();
        mDatabase = getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery(sql, null);
        return cursor;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insert(String tableName, ContentValues values) {
        sqLiteDataAccessHelper = new SQLiteDataAccessHelper(mContext);
        mDatabase = sqLiteDataAccessHelper.getWritableDatabase();
        return mDatabase.insert(tableName, null, values);
    }
}
