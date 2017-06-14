package net.pmsv.diadiemcaobang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.pmsv.diadiemcaobang.DTO.DiaDiemDTO;

public class GocChupActivity extends AppCompatActivity {
    ImageView imageViewGocChup;
    TextView textViewGocChup;
    ImageButton imageButtonNext, imageButtonPrev;
    Toolbar toolbar;

    private void init(){

        imageViewGocChup = (ImageView) findViewById(R.id.imageViewGocChup);
        textViewGocChup = (TextView) findViewById(R.id.textViewGocChup);
        imageButtonNext = (ImageButton) findViewById(R.id.imageButtoNext);
        imageButtonPrev = (ImageButton) findViewById(R.id.imageButtonPrev);
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



    }
}
