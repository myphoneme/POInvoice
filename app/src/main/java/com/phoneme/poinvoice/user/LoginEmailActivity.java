package com.phoneme.poinvoice.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.phoneme.poinvoice.MainActivity;
import com.phoneme.poinvoice.R;
import com.phoneme.poinvoice.config.RetrofitClientInstance;
import com.phoneme.poinvoice.interfaces.GetDataService;
import com.phoneme.poinvoice.user.network.OTPVerifactionResponse;
import com.scwang.wave.MultiWaveHeader;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginEmailActivity extends AppCompatActivity {

    private EditText email,password;
    private Button button;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email_new_ui);
        getSupportActionBar().hide();
        email=(EditText)findViewById(R.id.id_email);
        password=(EditText)findViewById(R.id.id_password);

        MultiWaveHeader waveHeader = findViewById(R.id.wavebottom);
        //waveHeader.setStartColor(R.color.wavecolor1);
        //waveHeader.setCloseColor(R.color.wavecolor2);
        waveHeader.setColorAlpha(.5f);
//
//        waveHeader.setWaveHeight(50);
//        waveHeader.setGradientAngle(360);
//        waveHeader.setProgress(.8f);
//        waveHeader.setVelocity(1f);
//        waveHeader.setScaleY(-1f);
//
//        waveHeader.setWaves("PairWave");
//
        waveHeader.start();
//        waveHeader.stop();
//        waveHeader.isRunning();

        button=(Button)findViewById(R.id.button);
        UserAuth userAuth=new UserAuth(this);
        if(userAuth.isJwtTokenValid()){
            Intent intent1=new Intent(this, MainActivity.class);
            startActivity(intent1);
            finish();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MOre logic later
                String email_text=email.getText().toString();
                String password_text=password.getText().toString();
                if(email_text!=null && email_text.length()>0 &&  Helper.isEmailValid(email_text) && password_text.length()>0){
                    postLoginData(email_text,password_text);
                }

//                startMainActivity();
            }
        });
    }

    private void startMainActivity(){
        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void postLoginData(String email,String password){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        HashMap<String, String> map=new HashMap<>();
        map.put("email",email);
        map.put("pwd",password);//postLoginPassword
        Call<OTPVerifactionResponse> call=service.postLoginPassword(map);
        call.enqueue(new Callback<OTPVerifactionResponse>() {
            @Override
            public void onResponse(Call<OTPVerifactionResponse> call, Response<OTPVerifactionResponse> response) {
                Toast.makeText(getApplicationContext(),"first="+response.body().getMessage(), Toast.LENGTH_LONG).show();

                if(response.isSuccessful() && response.body()!=null) {
                    if( response.body().getPasswordverified() && response.body().getJwttoken()!=null && !response.body().getJwttoken().isEmpty() && response.body().getJwttoken().length()>0){
                        Toast.makeText(getApplicationContext(),"Password matches.You're logged in2", Toast.LENGTH_LONG).show();
                        setDatainSharedPreferences(response.body().getJwttoken());
                        //ActivityStart();
                        startMainActivity();
                    }else{
                        Toast.makeText(getApplicationContext(),response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<OTPVerifactionResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error:"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void setDatainSharedPreferences(String token){
        UserAuth userAuth=new UserAuth(this);
        userAuth.setJwtToken(token);
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
