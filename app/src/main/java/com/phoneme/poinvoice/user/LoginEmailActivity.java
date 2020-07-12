package com.phoneme.poinvoice.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.phoneme.poinvoice.MainActivity;
import com.phoneme.poinvoice.R;

public class LoginEmailActivity extends AppCompatActivity {

    private EditText email,password;
    private Button button;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);
        email=(EditText)findViewById(R.id.id_email);
        password=(EditText)findViewById(R.id.id_password);

        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MOre logic later
                startMainActivity();
            }
        });
    }

    private void startMainActivity(){
        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
