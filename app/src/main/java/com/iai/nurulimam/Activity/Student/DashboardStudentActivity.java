package com.iai.nurulimam.Activity.Student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.iai.nurulimam.Presenter.StudentPresenter.StudentPresenter;
import com.iai.nurulimam.R;

public class DashboardStudentActivity extends AppCompatActivity {

    StudentPresenter presenter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_logout:
                presenter.logout();
                break;
            case R.id.cv_announcement:
                startActivity(new Intent(getApplicationContext(), StudentFeedActivity.class));
                break;
        }

    }


}