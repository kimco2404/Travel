package com.example.k.diadiembinhphuoc.DAL;

import android.content.Context;
import android.database.Cursor;

import com.example.k.diadiembinhphuoc.DTO.GocChupDTO;

import java.util.ArrayList;

/**
 * Created by USER on 6/17/2017.
 */

public class GocChupDAL extends  SQLiteDataAccessHelper {
    public GocChupDAL(Context context) {
        super(context);
    }
    public ArrayList<GocChupDTO> getGocChupByID(String idDiaDiem) {
        ArrayList<GocChupDTO> listgocchup = new ArrayList<GocChupDTO>();
        String query = "select * from GocChup where idDiaDiem " + " = '" + idDiaDiem + "'";
        Cursor data =getData(query);
        while (data.moveToNext()) {
            GocChupDTO GC = new GocChupDTO();
            GC.setId(data.getInt(0));
            GC.setIdDiaDiem(data.getString(1));
            GC.setNoiDung(data.getString(2));
            listgocchup.add(GC);
        }
        return listgocchup;
    }
}
