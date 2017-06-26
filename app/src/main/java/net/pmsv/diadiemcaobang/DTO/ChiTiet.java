package net.pmsv.diadiemcaobang.DTO;

/**
 * Created by May1 on 5/31/2017.
 */

public class ChiTiet {
    private String ten, thongtin;
    private String hinh, id;

    public ChiTiet(String ten, String hinh, String id) {
        this.ten = ten;
        this.hinh = hinh;
        this.id = id;
    }


    public ChiTiet() {
    }

    public String getTen() {
        return ten;
    }

    public String getThongTin() {
        return thongtin;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setThongTin(String thongtin) {
        this.thongtin = thongtin;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
