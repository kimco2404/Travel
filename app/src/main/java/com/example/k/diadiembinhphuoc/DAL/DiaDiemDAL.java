package com.example.k.diadiembinhphuoc.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.example.k.diadiembinhphuoc.DTO.DiaDiemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by may38 on 5/30/2017.
 */

public class DiaDiemDAL {
    private SQLiteDataAccessHelper dataAccessHelper;
    private Context context;
    private ContentValues contentValues  = null;

    public static final String ID = "id";
    public static final String TINH = "tinh";
    public static final String TEN = "ten";
    public static final String THONGTIN = "thongtin";
    public static final String DUONGDI = "duongdi";
    public static final String MACHNHO = "machnho";
    public static final String HINHDAIDIEN = "hinhdaidien";
    public static final String SOGOCCHUP = "sogocchup";
    public static final String THOIDIEM = "thoidiem";

    public DiaDiemDAL(Context context) {
        this.context = context;
    }

    public List<DiaDiemDTO> layDanhSachDiaDiem() {
        dataAccessHelper = new SQLiteDataAccessHelper(context);
        List<DiaDiemDTO> list = new ArrayList<>();
        Cursor data = dataAccessHelper.getData("select * from DiaDiem");
        while (data.moveToNext()) {
            DiaDiemDTO d = new DiaDiemDTO();
            d.setId(data.getString(0));
            d.setTinh(data.getInt(1));
            d.setTen(data.getString(2));
            d.setThongtin(data.getString(3));
            d.setDuongdi(data.getString(4));
            d.setMachnho(data.getString(5));
            d.setHinhdaidien(data.getString(6));
            d.setSogocchup(data.getInt(7));
            d.setThoidiem(data.getString(8));
            list.add(d);
        }
        return list;
    }


    public boolean addDiaDiem(DiaDiemDTO dto) {
        // set values
        setDiaDiem(dto);
        try {
            dataAccessHelper = new SQLiteDataAccessHelper(context);
            return dataAccessHelper.insert("DiaDiem", getContentValue()) > 0;
        } catch (SQLiteConstraintException ex){
            Log.w("", ex.getMessage());
            return false;
        }
    }


    public void setDiaDiem(DiaDiemDTO dto)
    {
        try
        {
            //mDatabase = this.getWritableDatabase();
            contentValues = new ContentValues();
            contentValues.put(ID,dto.getId());
            contentValues.put(TINH,dto.getTinh());
            contentValues.put(TEN,dto.getTen());
            contentValues.put(THONGTIN,dto.getThongtin());
            contentValues.put(DUONGDI,dto.getDuongdi());
            contentValues.put(MACHNHO,dto.getMachnho());
            contentValues.put(HINHDAIDIEN,dto.getHinhdaidien());
            contentValues.put(SOGOCCHUP,dto.getSogocchup());
            contentValues.put(THOIDIEM,dto.getThoidiem());
//            long result =  mDatabase.insert("DiaDiem", null, contentValues);
//            if (result == -1){
//                return false;
//            }else{
//                return true;
//            }
        }
        catch(Exception ex)
        {
            //ex.notify();
            //return false;
        }
    }

    private ContentValues getContentValue() {
        return contentValues;
    }


    public List<DiaDiemDTO> searchName(String searchName){
        dataAccessHelper = new SQLiteDataAccessHelper(context);
        List<DiaDiemDTO> listDiaDiem = new ArrayList<>();
        String query = "Select * from DiaDIem where ten like " + "'%" + searchName + "%'";
        Cursor cursor = dataAccessHelper.getData(query);
        while (cursor.moveToNext()) {
            DiaDiemDTO d = new DiaDiemDTO();
            d.setId(cursor.getString(0));
            d.setTinh(cursor.getInt(1));
            d.setTen(cursor.getString(2));
            d.setThongtin(cursor.getString(3));
            d.setDuongdi(cursor.getString(4));
            d.setMachnho(cursor.getString(5));
            d.setHinhdaidien(cursor.getString(6));
            d.setSogocchup(cursor.getInt(7));
            d.setThoidiem(cursor.getString(8));
            listDiaDiem.add(d);
        }
        cursor.close();
        return listDiaDiem;
    }
}
