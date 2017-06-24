package com.example.k.diadiembinhphuoc.DTO;

import java.io.Serializable;

/**
 * Created by USER on 6/17/2017.
 */

public class GocChupDTO implements Serializable {
    private int id;
    private String idDiaDiem;
    private String NoiDung;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdDiaDiem() {
        return idDiaDiem;
    }

    public void setIdDiaDiem(String idDiaDiem) {
        this.idDiaDiem = idDiaDiem;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public GocChupDTO(int id, String idDiaDiem, String noiDung) {
        this.id = id;
        this.idDiaDiem = idDiaDiem;
        NoiDung = noiDung;
    }

    public GocChupDTO() {
    }
}
