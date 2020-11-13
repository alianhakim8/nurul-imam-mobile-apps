package com.iai.nurulimam.Activity.Teacher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.iai.nurulimam.R;

public class TeacherProfileActivity extends AppCompatActivity {

    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);

        name = findViewById(R.id.tvName);
        SharedPreferences sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        name.setText(email);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                startActivity(new Intent(getApplicationContext(), DashboardTeacherActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                break;
        }
    }
}