package net.pmsv.diadiemcaobang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import net.pmsv.diadiemcaobang.BLL.DiaDiemBLL;

import net.pmsv.diadiemcaobang.DAL.SQLiteDataAccessHelper;

public class MainActivity extends AppCompatActivity {

    private SQLiteDataAccessHelper SQLiteDataAccessHelper;

    public Button btnDangNhap, btnDangKy, btnBoQua;
    public Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SQLiteDataAccessHelper = new SQLiteDataAccessHelper(this,"DLCaoBang.sqlite",null, 1);
        //final DiaDiemBLL diaDiemBLL = new DiaDiemBLL(this, SQLiteDataAccessHelper);


        btnDangKy = (Button) findViewById(R.id.btDangKy);
        btnDangNhap = (Button) findViewById(R.id.btDangNhap);
        btnBoQua = (Button) findViewById(R.id.btBoQua);
        myToolbar = (Toolbar) findViewById(R.id.toolbarChiTiet);
        setSupportActionBar(myToolbar);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itHome = new Intent(MainActivity.this, DiaDiemActivity.class);
                startActivity(itHome);
            }
        });


        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(home);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
