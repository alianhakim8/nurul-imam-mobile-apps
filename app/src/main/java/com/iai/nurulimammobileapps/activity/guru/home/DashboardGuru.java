package com.iai.nurulimammobileapps.activity.guru.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.iai.nurulimammobileapps.MainActivity;
import com.iai.nurulimammobileapps.R;

public class DashboardGuru extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_guru);
    }


    public void logoutOnClick(View view) {

    }

}
