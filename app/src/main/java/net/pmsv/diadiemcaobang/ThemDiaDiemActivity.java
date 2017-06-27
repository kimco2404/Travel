package net.pmsv.diadiemcaobang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.pmsv.diadiemcaobang.BLL.DiaDiemBLL;
import net.pmsv.diadiemcaobang.DTO.DiaDiemDTO;

import net.pmsv.diadiemcaobang.BLL.DiaDiemBLL;
import net.pmsv.diadiemcaobang.DTO.DiaDiemDTO;

public class ThemDiaDiemActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnThem,btnReset;
    private TextView txtId, txtTinh, txtTen ,txtThongTin, txtDươngDi, txtMachNho, txtHinhDaiDien, txtSoGocChup, txtThoiDiem;

    DiaDiemBLL diaDiemBLL = new DiaDiemBLL(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themdiadiem);
        init();

    }

    private void addDiaDiem()
    {
        DiaDiemDTO diaDiemDTO = new DiaDiemDTO(
                txtId.getText().toString(),
                Integer.parseInt(txtTinh.getText().toString()),
                txtTen.getText().toString(),
                txtThongTin.getText().toString(),
                txtDươngDi.getText().toString(),
                txtMachNho.getText().toString(),
                txtHinhDaiDien.getText().toString(),
                Integer.parseInt(txtSoGocChup.getText().toString()),
                txtThoiDiem.getText().toString());
        if (diaDiemBLL.addDiaDiem(diaDiemDTO)){
            Toast.makeText(this,"Thêm Thành Công",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ThemDiaDiemActivity.this,HomeActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Thêm Thất Bại",Toast.LENGTH_SHORT).show();
        }
    }

    private void init(){
        btnThem = (Button) findViewById(R.id.btnThem);
        btnReset = (Button) findViewById(R.id.btnReset);

        txtId = (TextView) findViewById(R.id.txtId);
        txtTinh = (TextView) findViewById(R.id.txtTinh);
        txtTen = (TextView) findViewById(R.id.txtTen);
        txtThongTin = (TextView) findViewById(R.id.txtThongTin);
        txtDươngDi = (TextView) findViewById(R.id.txtDuongDi);
        txtMachNho = (TextView) findViewById(R.id.txtMachNho);
        txtHinhDaiDien = (TextView) findViewById(R.id.txtHinhDaiDien);
        txtSoGocChup = (TextView) findViewById(R.id.txtSoGocChup);
        txtThoiDiem = (TextView) findViewById(R.id.txtThoiDiem);

        btnThem.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnThem){
            addDiaDiem();
        }else if(v.getId() == R.id.btnReset){
            txtId.setText("");
            txtTinh.setText("");
            txtTen.setText("");
            txtThongTin.setText("");
            txtDươngDi.setText("");
            txtMachNho.setText("");
            txtHinhDaiDien.setText("");
            txtSoGocChup.setText("");
            txtThoiDiem.setText("");
        }
    }
}