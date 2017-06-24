package com.example.k.diadiembinhphuoc.DTO;

/**
 * Created by K on 21/06/2017.
 */

public class UserDTO {
    private int id;
    private String hoten;
    private int sodienthoai;
    private String tendangnhap;
    private String matkhau;

    public UserDTO(String hoten, int sodienthoai, String tendangnhap, String matkhau) {
        this.hoten = hoten;
        this.sodienthoai = sodienthoai;
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
    }

    public UserDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(int sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
