package com.example.k.diadiembinhphuoc.BLL;

import android.content.Context;

import com.example.k.diadiembinhphuoc.DAL.*;
import com.example.k.diadiembinhphuoc.DTO.DiaDiemDTO;

import java.util.List;

/**
 * Created by may38 on 5/30/2017.
 */

public class DiaDiemBLL {
    private Context context;

    public DiaDiemBLL(Context context) {
        this.context = context;
    }

    public List<DiaDiemDTO> layDanhSachDiaDiem() {
        DiaDiemDAL diaDiemDAL = new DiaDiemDAL(context);
        return diaDiemDAL.layDanhSachDiaDiem();
    }

    public boolean addDiaDiem(DiaDiemDTO dto) {
        DiaDiemDAL diaDiemDAL = new DiaDiemDAL(context);
        return diaDiemDAL.addDiaDiem(dto);
    }

    public List<DiaDiemDTO> searchName(String searchName){
        DiaDiemDAL diaDiemDAL = new DiaDiemDAL(context);
        return diaDiemDAL.searchName(searchName);
    }
}
