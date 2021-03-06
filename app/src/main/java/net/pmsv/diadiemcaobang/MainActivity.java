package net.pmsv.diadiemcaobang;

import android.accessibilityservice.GestureDescription;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import net.pmsv.diadiemcaobang.BLL.DiaDiemBLL;

import net.pmsv.diadiemcaobang.BLL.UserBLL;
import net.pmsv.diadiemcaobang.DAL.SQLiteDataAccessHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    public Button btnDangNhap, btnDangKy, btnBoQua;
    public Toolbar myToolbar;
    public EditText txtDangNhap, txtMatKhau;

    //gm
    private SignInButton SignIn;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;

    //fb
    LoginButton loginButton;
    CallbackManager callbackManager;
    String name, email,img_url,userID;

    private SQLiteDataAccessHelper dataAccessHelper;

    UserBLL userBLL = new UserBLL(this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);
        dbCopy();
        control();

//        btnDangKy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, DangkyActivity.class);
//                startActivity(intent);
//            }
//        });

//
//        btnDangNhap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               // Cursor kt = userBLL.kiemTraDangNhap();
//                Cursor kt = dataAccessHelper.getData("Select * from User");
//                while (kt.moveToNext()) {
//                    if (txtDangNhap.getText().toString().equals(" ") == false && txtMatKhau.getText().toString().equals(" ") == false) {
//                        Toast.makeText(MainActivity.this, "Chua nhap tai khoan mat khau", Toast.LENGTH_SHORT).show();
//                    }
////                    else if(txtDangNhap.getText().toString().equals(" ") == true && txtMatKhau.getText().toString().equals(" ") == true){
//                    else {
//                        if (!txtDangNhap.getText().equals((kt.getString(3))) && !txtMatKhau.getText().equals((kt.getString(4)))) {
//                            Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                            Intent home = new Intent(MainActivity.this, HomeActivity.class);
//                            startActivity(home);
//
//                        } else {
//                            Toast.makeText(MainActivity.this, "Sai Tai Khoan ", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//            }
//        });

        btnBoQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoginButton();
            }
        });
    }

    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(SQLiteDataAccessHelper.DBNAME);
            String outFileName = SQLiteDataAccessHelper.DBLOCATION + SQLiteDataAccessHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("HomeActivity", "DB copied");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void dbCopy(){
        dataAccessHelper = new SQLiteDataAccessHelper(this);
        File database = getApplicationContext().getDatabasePath(SQLiteDataAccessHelper.DBNAME);
        if (false == database.exists()) {
            dataAccessHelper.getReadableDatabase();
            //Copy db
            if (copyDatabase(MainActivity.this)) {
                Toast.makeText(MainActivity.this, "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void control(){
        SignIn = (SignInButton) findViewById(R.id.bn_login);
        SignIn.setOnClickListener(this);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();

        loginButton = (LoginButton)findViewById(R.id.login_button);//fb
        loginButton.setOnClickListener(this);
        btnDangKy = (Button) findViewById(R.id.btDangKy);
        btnDangKy.setOnClickListener(this);
        btnDangNhap = (Button) findViewById(R.id.btDangNhap);
        btnDangNhap.setOnClickListener(this);
        btnBoQua = (Button) findViewById(R.id.btBoQua);
        btnBoQua.setOnClickListener(this);
        myToolbar = (Toolbar) findViewById(R.id.toolbarChiTiet);
        txtDangNhap = (EditText)findViewById(R.id.txtDangNhap);
        txtMatKhau = (EditText)findViewById(R.id.txtMatKhau);
        setSupportActionBar(myToolbar);
    }

    private void dangNhap() {
        if (txtDangNhap.getText().toString().equals("") == true && txtMatKhau.getText().toString().equals("") == true) {
            Toast.makeText(MainActivity.this, "Chua nhap tai khoan mat khau", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Cursor kt = dataAccessHelper.getData("Select * from User");
            while (kt.moveToNext()) {
                    if (!txtDangNhap.getText().toString().equals(kt.getString(3)) == false && !txtMatKhau.getText().toString().equals(kt.getString(4) )  == false) {
                       Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent home = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(home);
                    } else {
                        Toast.makeText(MainActivity.this, "Sai Tai Khoan ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //facebook
    public void setLoginButton(){
        loginButton.setReadPermissions(Arrays.asList("public_profile","email"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                result();
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Login Facebook cancelled.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, "Login Facebook Error.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void result(){
        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("JSON",response.getJSONObject().toString());
                try {
                    email = object.getString("email");
                    name = object.getString("name");
                    userID = object.getString("id");
                    if(response != null) {

                        SharedPreferences sharedPreferences = getSharedPreferences("fdata", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name", name);
                        editor.putString("email", email);
                        editor.putString("userID", userID);
                        editor.apply();

                        Toast.makeText(MainActivity.this,"Login successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name,email");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }else {
            callbackManager.onActivityResult(requestCode, resultCode, data); //fb
        }
    }


    public void signIn(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);
    }

    public void handleResult(GoogleSignInResult result){

        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            name = account.getDisplayName();
            email = account.getEmail();
            img_url = account.getPhotoUrl().toString();

            SharedPreferences sharedPreferences = getSharedPreferences("gdata", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name",name);
            editor.putString("email",email);
            editor.putString("img_url",img_url);
            editor.apply();
            //editor.commit();
            Toast.makeText(MainActivity.this,"Login successfully",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bn_login:
                signIn();
                break;
            case R.id.btDangNhap:
                dangNhap();
                break;
            case R.id.btDangKy:
                Intent intent = new Intent(MainActivity.this, DangkyActivity.class);
                startActivity(intent);
                break;
            case R.id.btBoQua:
                finish();
                System.exit(0);
                break;
            case R.id.login_button:
                setLoginButton();
                break;
        }
    }
}
