package com.phoneme.poinvoice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.phoneme.poinvoice.user.LoginEmailActivity;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(getApplicationContext(), LoginEmailActivity.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}
