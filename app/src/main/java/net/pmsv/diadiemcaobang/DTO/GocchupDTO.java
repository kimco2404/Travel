package net.pmsv.diadiemcaobang.DTO;

import java.io.Serializable;

/**
 * Created by K on 20/06/2017.
 */

public class GocchupDTO implements Serializable {
    private int id;
    private String gocChup;
    private String[] hinhAnh;

    public String getGocChup() {
        return gocChup;
    }

    public void setGocChup(String gocChup) {
        this.gocChup = gocChup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public GocchupDTO(int id, String gocChup, String[] hinhAnh) {
        this.id = id;
        this.gocChup = gocChup;
        this.hinhAnh = hinhAnh;
    }
    public GocchupDTO() {

    }
}
