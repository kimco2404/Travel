package net.pmsv.diadiemcaobang.BLL;

import android.content.Context;
import android.database.Cursor;

import net.pmsv.diadiemcaobang.DAL.DiaDiemDAL;
import net.pmsv.diadiemcaobang.DAL.UserDAL;
import net.pmsv.diadiemcaobang.DTO.DiaDiemDTO;
import net.pmsv.diadiemcaobang.DTO.UserDTO;

import java.util.List;

/**
 * Created by may38 on 5/30/2017.
 */

public class UserBLL {
    private Context context;

    public UserBLL(Context context) {
        this.context = context;
    }

    public boolean addUser(UserDTO dto) {
        UserDAL userDAL = new UserDAL(context);
        return userDAL.addUser(dto);
    }
    public Cursor kiemTraDangNhap() {
        UserDAL userDAL = new UserDAL(context);
        return userDAL.kiemTraDangNhap();
    }
}
