package com.example.k.diadiembinhphuoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.k.diadiembinhphuoc.BLL.UserBLL;
import com.example.k.diadiembinhphuoc.DTO.UserDTO;

/**
 * Created by K on 21/06/2017.
 */

public class DangkyActivity extends AppCompatActivity implements View.OnClickListener{


    Button btnThem,btnReset;
    EditText  txtHoTen, txtSoDienThoai, txtTenDangNhap, txtMatKhau;

    UserBLL userBLL = new UserBLL(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        init();
    }

    private void addDUser()
    {
        try{
            UserDTO userDTO = new UserDTO(txtHoTen.getText().toString(),
                    Integer.parseInt(txtSoDienThoai.getText().toString()),
                    txtTenDangNhap.getText().toString(),
                    txtMatKhau.getText().toString());
            if (userBLL.addUser(userDTO)){
                Toast.makeText(this,"Thêm Thành Công",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DangkyActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }catch (Exception ex){
            Toast.makeText(this,"Thêm Thất Bại",Toast.LENGTH_LONG).show();
        }
    }

    private void init(){
        btnThem = (Button) findViewById(R.id.btnThem);
        btnReset = (Button) findViewById(R.id.btnReset);

        txtHoTen = (EditText) findViewById(R.id.txtHoTen);
        txtSoDienThoai = (EditText) findViewById(R.id.txtSoDienThoai);
        txtTenDangNhap = (EditText) findViewById(R.id.txtTenDangNhap);
        txtMatKhau = (EditText) findViewById(R.id.txtMatKhau);

        btnThem.setOnClickListener(this);
        btnReset.setOnClickListener(this);
    }

    private void resetText(){
        txtHoTen.setText("");
        txtSoDienThoai.setText("");
        txtTenDangNhap.setText("");
        txtMatKhau.setText("");
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnThem){
            addDUser();
        }else if(v.getId() == R.id.btnReset){
            resetText();
        }
    }
}
