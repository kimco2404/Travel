package net.pmsv.diadiemangiang.DAL;

import android.content.Context;
import android.database.Cursor;

import net.pmsv.diadiemangiang.DTO.DiaDiemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by may38 on 5/30/2017.
 */

public class DiaDiemDAL {
    private SQLiteDataAccessHelper dataAccessHelper;
    private Context context;

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
    public List<DiaDiemDTO> insertDiaDiem() {
        dataAccessHelper = new SQLiteDataAccessHelper(context);
        List<DiaDiemDTO> list = new ArrayList<>();
        Cursor data = dataAccessHelper.getData("INSERT INTO DiaDiem ");
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
    public List<DiaDiemDTO>  DeleteDiaDiem()
    {
        List<DiaDiemDTO> list = new ArrayList<>();
        return list;
    }
}
