package com.example.k.diadiembinhphuoc.BLL;

import android.content.Context;

import com.example.k.diadiembinhphuoc.DAL.HinhAnhDAL;
import com.example.k.diadiembinhphuoc.DTO.HinhAnhDTO;

import java.util.List;

/**
 * Created by USER on 6/17/2017.
 */

public class HinhAnhBLL {

    private Context context;

    public HinhAnhBLL(Context context) {
        this.context = context;
    }

    public List<HinhAnhDTO> layDanhSachHinhAnh() {
        HinhAnhDAL hinhAnhDAL = new HinhAnhDAL(context);
        return hinhAnhDAL.layDanhSachHinhAnh();
    }

    public List<HinhAnhDTO> getHinhAnhByIDGocChup(String idDiaDiem) {
        HinhAnhDAL hinhAnhDAL = new HinhAnhDAL(context);
        return hinhAnhDAL.getHinhAnhByIDGocChup(idDiaDiem);
    }
}
