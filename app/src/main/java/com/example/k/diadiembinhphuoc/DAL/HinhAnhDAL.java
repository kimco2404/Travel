package com.example.k.diadiembinhphuoc.DAL;

import android.content.Context;
import android.database.Cursor;

import com.example.k.diadiembinhphuoc.DTO.HinhAnhDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 6/17/2017.
 */

public class HinhAnhDAL extends  SQLiteDataAccessHelper {

    public HinhAnhDAL(Context context) {
        super(context);
    }
    public List<HinhAnhDTO> layDanhSachHinhAnh() {
        ArrayList<HinhAnhDTO> listhinhanh = new ArrayList<HinhAnhDTO>();
        String query = "select * from HinhAnh";
        Cursor data = getData(query);
        while (data.moveToNext()) {
            HinhAnhDTO dto = new HinhAnhDTO();
            dto.setId(data.getInt(0));
            dto.setIdGocChup(data.getInt(1));
            dto.setTenHinh(data.getString(2));
            listhinhanh.add(dto);
        }
        return listhinhanh;
    }

    public ArrayList<HinhAnhDTO> getHinhAnhByIDGocChup(String idDiaDiem) {
        ArrayList<HinhAnhDTO> listhinhanh = new ArrayList<HinhAnhDTO>();
        String query = "select * from HinhAnh where idDiaDiem " + " = '" + idDiaDiem + "'";
        Cursor data =getData(query);
        while (data.moveToNext()) {
            HinhAnhDTO HA = new HinhAnhDTO();
            HA.setId(data.getInt(0));
            HA.setIdGocChup(data.getInt(1));
            HA.setTenHinh(data.getString(2));
            HA.setIdDiaDiem(data.getString(3));
            listhinhanh.add(HA);
        }
        return listhinhanh;

    }
}
