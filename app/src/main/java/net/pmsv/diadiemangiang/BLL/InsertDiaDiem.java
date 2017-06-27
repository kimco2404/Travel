package net.pmsv.diadiemangiang.BLL;


import android.content.Context;

import net.pmsv.diadiemangiang.DAL.DiaDiemDAL;
import net.pmsv.diadiemangiang.DTO.DiaDiemDTO;

import java.util.List;

/**
 * Created by KimCo on 6/14/2017.
 */

public class InsertDiaDiem {
    private Context  context;

    public InsertDiaDiem(Context context)
    {
        this.context = context;
    }
    public   List<DiaDiemDTO> InsertDiaDiem() {
        DiaDiemDAL diaDiemDAL = new DiaDiemDAL(context);
        return diaDiemDAL.insertDiaDiem();
    }
}
