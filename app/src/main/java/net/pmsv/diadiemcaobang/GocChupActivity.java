package net.pmsv.diadiemcaobang;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import net.pmsv.diadiemcaobang.Adapter.AdapterGocChup;
import net.pmsv.diadiemcaobang.BLL.GocChupBLL;
import net.pmsv.diadiemcaobang.BLL.HinhAnhBLL;
import net.pmsv.diadiemcaobang.DTO.DiaDiemDTO;
import net.pmsv.diadiemcaobang.DTO.GocChupDTO;
import net.pmsv.diadiemcaobang.DTO.HinhAnhDTO;

import java.util.List;

public class GocChupActivity extends AppCompatActivity {
    

    TextView textViewGocChup;
    ImageButton imageButtonNext, imageButtonPrev;
    Toolbar toolbar;

    //goc chup
    AdapterGocChup adapterGocChup;
    ViewPager viewPager;
    List<GocChupDTO> gocChupList;
    List<HinhAnhDTO> hinhAnhList;
    Cursor hinhAnh;
    private void init(){
        viewPager = (ViewPager) findViewById(R.id.imageViewGocChup);
        textViewGocChup = (TextView) findViewById(R.id.textViewGocChup);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goc_chup);
        init();
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        DiaDiemDTO diaDiemDTO = (DiaDiemDTO) intent.getSerializableExtra("data");
        toolbar = (Toolbar) findViewById(R.id.toolbarGocChup);
        setSupportActionBar(toolbar);
        toolbar.setTitle(diaDiemDTO.getTen());
        Toast.makeText(GocChupActivity.this, diaDiemDTO.getTen()
                ,Toast.LENGTH_SHORT).show();



        //goc chup
//        adapterGocChup = new AdapterGocChup(this);
//        viewPager.setAdapter(adapterGocChup);


        //goc chup new
//        GocChupBLL gocChupBLL = new GocChupBLL(this);
//        gocChupList = gocChupBLL.getGocChupByID(diaDiemDTO.getId());
//
//        HinhAnhBLL hinhAnhBLL = new HinhAnhBLL(this);
//        hinhAnh = hinhAnhBLL.getHinhAnhByIDGocChup(diaDiemDTO.getId());
//
//        viewPager = (ViewPager)findViewById(R.id.imageViewGocChup);
//        adapterGocChup = new AdapterGocChup(GocChupActivity.this,hinhAnhList,gocChupList);
//        viewPager.setAdapter(adapterGocChup);
    }
}
