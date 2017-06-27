package com.example.k.diadiembinhphuoc.BLL;

import android.content.Context;

import com.example.k.diadiembinhphuoc.DAL.GocChupDAL;
import com.example.k.diadiembinhphuoc.DTO.GocChupDTO;

import java.util.List;

/**
 * Created by USER on 6/17/2017.
 */

public class GocChupBLL {
    private Context context;

    public GocChupBLL(Context context) {
        this.context = context;
    }

    public List<GocChupDTO> getGocChupByID(String idDiaDiem) {
        GocChupDAL gocChupDAL = new GocChupDAL(context);
        return gocChupDAL.getGocChupByID(idDiaDiem);
    }
}
