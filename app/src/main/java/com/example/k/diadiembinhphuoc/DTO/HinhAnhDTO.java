package com.example.k.diadiembinhphuoc.DTO;

import java.io.Serializable;

/**
 * Created by USER on 6/17/2017.
 */

public class HinhAnhDTO implements Serializable {
    private int id;
    private int idGocChup;
    private String TenHinh;
    private  String idDiaDiem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGocChup() {
        return idGocChup;
    }

    public void setIdGocChup(int idGocChup) {
        this.idGocChup = idGocChup;
    }

    public String getTenHinh() {
        return TenHinh;
    }

    public void setTenHinh(String tenHinh) {
        TenHinh = tenHinh;
    }

    public String getIdDiaDiem() {
        return idDiaDiem;
    }

    public void setIdDiaDiem(String idDiaDiem) {
        this.idDiaDiem = idDiaDiem;
    }

    public HinhAnhDTO(int id, int idGocChup, String tenHinh, String idDiaDiem) {
        this.id = id;
        this.idGocChup = idGocChup;
        TenHinh = tenHinh;
        this.idDiaDiem = idDiaDiem;
    }

    public HinhAnhDTO() {
    }
}
