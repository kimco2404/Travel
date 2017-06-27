package net.pmsv.diadiemcaobang.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import net.pmsv.diadiemcaobang.DTO.GocChupDTO;

import java.util.ArrayList;
import java.util.List;

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
