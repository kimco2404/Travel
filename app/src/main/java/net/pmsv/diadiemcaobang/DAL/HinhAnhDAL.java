package net.pmsv.diadiemcaobang.DAL;

import android.content.Context;
import android.database.Cursor;

import net.pmsv.diadiemcaobang.DTO.HinhAnhDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 6/17/2017.
 */

public class HinhAnhDAL {


    private SQLiteDataAccessHelper dataAccessHelper;
    private Context context;
    Cursor data;

    public HinhAnhDAL(Context context) {
        this.context = context;
    }

    public List<HinhAnhDTO> layDanhSachHinhAnh() {
        dataAccessHelper = new SQLiteDataAccessHelper(context);
        List<HinhAnhDTO> list = new ArrayList<>();
        String query = "select * from HinhAnh";
        Cursor data = dataAccessHelper.getData(query);
        while (data.moveToNext()) {
            HinhAnhDTO dto = new HinhAnhDTO();
            dto.setIdHinhAnh(data.getInt(0));
            dto.setIdGocChup(data.getInt(1));
            dto.setTenHinh(data.getString(2));
            list.add(dto);
        }
        return list;
    }

    public Cursor getHinhAnhByIDGocChup(String idDiaDiem) {
        dataAccessHelper = new SQLiteDataAccessHelper(context);
        String query = "select * from HinhAnh, GocChup where GocChup.DiaDiem = '" + idDiaDiem +"' and GocChup.id = HinhAnh.GocChup";
        Cursor data = dataAccessHelper.getData(query);
        return data;
    }
}
