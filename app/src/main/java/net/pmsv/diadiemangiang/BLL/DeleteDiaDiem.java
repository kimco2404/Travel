package net.pmsv.diadiemangiang.BLL;

import android.content.Context;

import net.pmsv.diadiemangiang.DAL.DiaDiemDAL;
import net.pmsv.diadiemangiang.DTO.DiaDiemDTO;

import java.util.List;

/**
 * Created by may38 on 5/30/2017.
 */

public class DeleteDiaDiem {
    private Context context;

    public DeleteDiaDiem(Context context) {
        this.context = context;
    }

    public List<DiaDiemDTO> DeleteDiaDiem() {
        DiaDiemDAL diaDiemDAL = new DiaDiemDAL(context);
        return diaDiemDAL.DeleteDiaDiem();
    }
}
