package com.example.k.diadiembinhphuoc;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.k.diadiembinhphuoc.Adapter.GocChupAdapter;
import com.example.k.diadiembinhphuoc.BLL.GocChupBLL;
import com.example.k.diadiembinhphuoc.BLL.HinhAnhBLL;
import com.example.k.diadiembinhphuoc.DTO.DiaDiemDTO;
import com.example.k.diadiembinhphuoc.DTO.GocChupDTO;
import com.example.k.diadiembinhphuoc.DTO.HinhAnhDTO;

import java.util.List;

public class GocChupActivity extends AppCompatActivity {
    

    Toolbar toolbar;
    static int NUM_ITEMS =0;
    //goc chup
    GocChupAdapter adapterGocChup = new GocChupAdapter(this);
    ViewPager viewPager;
    List<GocChupDTO> gocChupList;
    List<HinhAnhDTO> hinhAnhList;
    String img;
    int idGocChup;
    Cursor hinhAnh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goc_chup);
        Intent intent = getIntent();
        GocChupBLL gocChupBLL = new GocChupBLL(this);
        HinhAnhBLL hinhAnhBLL = new HinhAnhBLL(this);
        String data = intent.getStringExtra("data");
        DiaDiemDTO diaDiemDTO = (DiaDiemDTO) intent.getSerializableExtra("data");
        toolbar = (Toolbar) findViewById(R.id.toolbarGocChup);
        setSupportActionBar(toolbar);
        toolbar.setTitle(diaDiemDTO.getTen());
        String idDiaDiem= diaDiemDTO.getId();
        gocChupList=gocChupBLL.getGocChupByID(idDiaDiem);
        hinhAnhList= hinhAnhBLL.getHinhAnhByIDGocChup(idDiaDiem);
        adapterGocChup= new GocChupAdapter(this,gocChupList,hinhAnhList);
        init();
    }

    private void init(){
        viewPager = (ViewPager) findViewById(R.id.imageViewGocChup);
        viewPager.setAdapter(adapterGocChup);
    }
}
