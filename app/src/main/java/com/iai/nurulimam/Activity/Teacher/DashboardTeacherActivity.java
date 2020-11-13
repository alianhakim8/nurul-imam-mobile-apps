package com.iai.nurulimam.Activity.Teacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.iai.nurulimam.Preferences.Preferences;
import com.iai.nurulimam.Presenter.TeacherPresenter.TeacherPresenter;
import com.iai.nurulimam.R;
import com.iai.nurulimam.View.TeacherView.TeacherView;

public class DashboardTeacherActivity extends AppCompatActivity implements TeacherView {
    private TeacherPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        // Calling Shared Preferences
        Preferences preferences = new Preferences(this);

        // Calling Guru Presenter
        presenter = new TeacherPresenter(preferences, this, this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_logout:
                presenter.logout();
                break;
            case R.id.cv_data:
                break;
            case R.id.cv_rpp:
                startActivity(new Intent(getApplicationContext(), TeacherPlanActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                break;
            case R.id.cv_personal:
                startActivity(new Intent(getApplicationContext(), TeacherProfileActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                break;
            case R.id.cvPost:
                startActivity(new Intent(getApplicationContext(), TeacherPostActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                break;


        }
    }

}