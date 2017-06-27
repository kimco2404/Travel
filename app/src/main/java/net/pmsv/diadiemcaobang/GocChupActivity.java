package net.pmsv.diadiemcaobang;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import net.pmsv.diadiemcaobang.Adapter.GocChupAdapter;
import net.pmsv.diadiemcaobang.BLL.GocChupBLL;
import net.pmsv.diadiemcaobang.BLL.HinhAnhBLL;
import net.pmsv.diadiemcaobang.DTO.DiaDiemDTO;
import net.pmsv.diadiemcaobang.DTO.GocChupDTO;
import net.pmsv.diadiemcaobang.DTO.HinhAnhDTO;

import java.util.ArrayList;
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
