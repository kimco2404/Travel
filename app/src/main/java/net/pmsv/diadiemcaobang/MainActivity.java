package net.pmsv.diadiemcaobang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
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

import net.pmsv.diadiemcaobang.DAL.SQLiteDataAccessHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    private SQLiteDataAccessHelper SQLiteDataAccessHelper;

    public Button btnDangNhap, btnDangKy, btnBoQua;
    public Toolbar myToolbar;


    //gm
    private SignInButton SignIn;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;

    //fb
    LoginButton loginButton;
    CallbackManager callbackManager;
    String name, email,img_url,userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);
        control();
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
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoginButton();
            }
        });
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bn_login:
                signIn();
                break;
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
}
