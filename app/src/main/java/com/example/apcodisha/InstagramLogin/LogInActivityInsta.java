package com.example.apcodisha.InstagramLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.apcodisha.R;

public class LogInActivityInsta extends AppCompatActivity implements AuthenticationListener {

    Button instagramLogin;
    AuthenticationDialog authenticationDialog;
    private String token = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_insta);

        instagramLogin = (Button)findViewById(R.id.instagram_login);

        instagramLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callAuthenticationDialog();

            }
        });
    }

    public void callAuthenticationDialog(){
        authenticationDialog = new AuthenticationDialog(LogInActivityInsta.this,this);
        authenticationDialog.setCancelable(true);
        authenticationDialog.show();
    }

    @Override
    public void onTokenReceived(String auth_token) {
        if(auth_token == null){
            return;
        }
        else{
            Log.i("AccessToken",auth_token);
        }

    }
}