package com.example.k.diadiembinhphuoc.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.example.k.diadiembinhphuoc.DTO.UserDTO;

/**
 * Created by may38 on 5/30/2017.
 */

public class UserDAL {
    private SQLiteDataAccessHelper dataAccessHelper;
    private Context context;
    private ContentValues contentValues  = null;

    public static final String ID = "id";
    public static final String HOTEN = "hoten";
    public static final String SODIENTHOAI = "sodienthoai";
    public static final String TENDANGNHAP = "tendangnhap";
    public static final String MATKHAU = "matkhau";

    public UserDAL(Context context) {
        this.context = context;
    }



    public boolean addUser(UserDTO dto) {
        // set values
        setUser(dto);
        try {
            dataAccessHelper = new SQLiteDataAccessHelper(context);
            return dataAccessHelper.insert("User", getContentValue()) > 0;
        } catch (SQLiteConstraintException ex){
            Log.w("", ex.getMessage());
            return false;
        }
    }

    public void setUser(UserDTO dto)
    {
        try
        {
            contentValues = new ContentValues();
            contentValues.put(HOTEN,dto.getHoten());
            contentValues.put(SODIENTHOAI,dto.getSodienthoai());
            contentValues.put(TENDANGNHAP,dto.getTendangnhap());
            contentValues.put(MATKHAU,dto.getMatkhau());
        }
        catch(Exception ex)
        {
        }
    }

    private ContentValues getContentValue() {
        return contentValues;
    }


    public Cursor kiemTraDangNhap() {
        dataAccessHelper = new SQLiteDataAccessHelper(context);
        String query = "select * from User";
        Cursor data = dataAccessHelper.getData(query);
        return data;
    }
}
