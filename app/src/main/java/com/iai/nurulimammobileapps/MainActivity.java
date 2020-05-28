package com.iai.nurulimammobileapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.iai.nurulimammobileapps.activity.guru.loginActivity.Login;

public class MainActivity extends AppCompatActivity {
    Button loginGuru, loginOrtu, loginSiswa;
    TextView daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uiReference();
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGuru:
                Intent login = new Intent(getApplicationContext(), Login.class);
                login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(login);
                //finish();
                break;
        }
    }

    public void uiReference() {
        loginGuru = findViewById(R.id.btnGuru);
        loginOrtu = findViewById(R.id.btnOrtu);
        loginSiswa = findViewById(R.id.btnSiswa);
        daftar = findViewById(R.id.daftar);
    }
}
