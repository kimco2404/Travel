package net.pmsv.diadiemcaobang.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.daimajia.androidanimations.library.sliders.SlideInRightAnimator;

import net.pmsv.diadiemcaobang.DTO.DiaDiemDTO;
import net.pmsv.diadiemcaobang.DTO.GocchupDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by may38 on 5/30/2017.
 */

public class GocChupDAL {
    private SQLiteDataAccessHelper dataAccessHelper;
    private Context context;
    private ContentValues contentValues  = null;

    public GocChupDAL(Context context) {
        this.context = context;
    }
    public List<GocchupDTO> searchName(String searchName) {
        dataAccessHelper = new SQLiteDataAccessHelper(context);
        List<GocchupDTO> listGocChup = new ArrayList<>();
        String query = "Select * from DiaDIem where Ten like " + "'%" + searchName + "%'";
        Cursor cursor = dataAccessHelper.getData(query);
        while (cursor.moveToNext()) {
            GocchupDTO d = new GocchupDTO();
            d.setId(cursor.getInt(0));
            d.setGocChup(cursor.getString(1));
            listGocChup.add(d);
        }
        cursor.close();
        return listGocChup;
    }

}
