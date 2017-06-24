package com.example.k.diadiembinhphuoc.BLL;

import android.content.Context;
import android.database.Cursor;

import com.example.k.diadiembinhphuoc.DAL.UserDAL;
import com.example.k.diadiembinhphuoc.DTO.UserDTO;

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
