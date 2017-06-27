package net.pmsv.diadiemcaobang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ViewSwitcher;

import net.pmsv.diadiemcaobang.Adapter.AdaperChiTiet;
import net.pmsv.diadiemcaobang.BLL.DiaDiemBLL;
import net.pmsv.diadiemcaobang.DTO.ChiTiet;

import java.util.ArrayList;

public class DiaDiemDetailActivity extends AppCompatActivity {

    ListView listViewChiTiet;
    ArrayList<ChiTiet> chiTiets;
    AdaperChiTiet adaperChiTiet, adaperChiTietSpinner;
    ImageSwitcher imageSwitcher;
    int[] hinh;
    int count;
    int current = 0;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_diem_detail);
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");


        init();

        adaperChiTiet = new AdaperChiTiet(this, R.layout.list_chi_tiet, chiTiets);
        listViewChiTiet.setAdapter(adaperChiTiet);


        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                // TODO Auto-generated method stub

                final ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        });
        imageSwitcher.setImageResource(hinh[0]);
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);
        imageSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current++;
                //  Check If index reaches maximum then reset it
                if (current == count)
                    current = 0;
                imageSwitcher.setImageResource(hinh[current]); // set the image in ImageSwitcher
            }
        });

    }

//    private void init() {
//
//        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
//        listViewChiTiet = (ListView) findViewById(R.id.listviewChiTiet);
//        chiTiets = new ArrayList<>();
//        DiaDiemBLL diaDiemBLL = new DiaDiemBLL(this);
//        chiTiets = new ArrayList<>();
//        chiTiets = diaDiemBLL.DiaDiemDetail(id);
//
//        hinh = new int[]{R.drawable.ag_image_1,R.drawable.ag_image_2,R.drawable.ag_image_3,R.drawable.ag_image_4,R.drawable.ag_image_5,R.drawable.ag_image_6,R.drawable.ag_image_7,R.drawable.ag_image_8,R.drawable.ag_image_9,R.drawable.ag_image_10,R.drawable.ag_image_11,R.drawable.ag_image_12,R.drawable.ag_image_13};
//        count = hinh.length;
//
//    }
    private void init() {

        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        listViewChiTiet = (ListView) findViewById(R.id.listviewChiTiet);
        chiTiets = new ArrayList<>();
        chiTiets.add(new ChiTiet("Thông Tin", R.drawable.thong_tin, 1));
        chiTiets.add(new ChiTiet("Thời Điểm", R.drawable.thoi_diem, 2));
        chiTiets.add(new ChiTiet("Đường Đi", R.drawable.duong_di, 3));
        chiTiets.add(new ChiTiet("Góc Chụp", R.drawable.dia_chi, 4));
        chiTiets.add(new ChiTiet("Mách nhỏ", R.drawable.mach_nho, 5));

        hinh = new int[]{R.drawable.ag_image_1,R.drawable.ag_image_2,R.drawable.ag_image_3,R.drawable.ag_image_4,R.drawable.ag_image_5,R.drawable.ag_image_6,R.drawable.ag_image_7,R.drawable.ag_image_8,R.drawable.ag_image_9,R.drawable.ag_image_10,R.drawable.ag_image_11,R.drawable.ag_image_12,R.drawable.ag_image_13};
        count = hinh.length;

    }
}

