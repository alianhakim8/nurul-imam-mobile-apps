package com.iai.nurulimam.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import com.google.android.material.textfield.TextInputLayout;
import com.iai.nurulimam.Activity.Parent.ParentActivity;
import com.iai.nurulimam.Activity.Student.DashboardStudentActivity;
import com.iai.nurulimam.Activity.Teacher.DashboardTeacherActivity;
import com.iai.nurulimam.Preferences.Preferences;
import com.iai.nurulimam.Presenter.Login.LoginPresenter;
import com.iai.nurulimam.R;
import com.iai.nurulimam.View.LoginView;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private LoginPresenter presenter;
    private Preferences preferences;
    private TextInputLayout etEmail, etPassword;
    private RadioGroup rg;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // ui references
        view();
        // presenter
        preferences = new Preferences(this);
        presenter = new LoginPresenter(this, this, preferences);
//        session();
        // initialization
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPass);

    }

    private void view() {
        progressDialog = new ProgressDialog(this);
        rg = findViewById(R.id.radioGroup);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String email = Objects.requireNonNull(etEmail.getEditText()).getText().toString().trim();
                String password = Objects.requireNonNull(etPassword.getEditText()).getText().toString().trim();
                int selectedId = rg.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                int idValue = 0;
                switch (selectedId) {
                    case R.id.teacherRb:
                        idValue = 1;
                        break;
                    case R.id.parentRb:
                        idValue = 2;
                        break;
                    case R.id.studentRb:
                        idValue = 3;
                        break;
                }
                presenter.login(email, password, idValue);
                break;
        }
    }

    @Override
    public void loginValidation() {
        Toast.makeText(this, "Harap Lengkapi Data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void teacherLogin(int id, String email, String token) {
        preferences.saveSPBoolean(Preferences.TEACHER_SESSION, true);
        SharedPreferences sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", id);
        editor.putString("email", email);
        editor.putString("token", token);
        editor.apply();
        startActivity(new Intent(getApplicationContext(), DashboardTeacherActivity.class));
        finish();
        //Toast.makeText(this, "Anda Guru", Toast.LENGTH_LONG).show();
    }

    @Override
    public void parentLogin() {
        preferences.saveSPBoolean(Preferences.PARENT_SESSION, true);
        startActivity(new Intent(getApplicationContext(), ParentActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
        finish();
        Toast.makeText(this, "Anda Ortu", Toast.LENGTH_LONG).show();
    }

    @Override
    public void studentLogin(int id, String email, String token) {
        preferences.saveSPBoolean(Preferences.STUDENT_SESSION, true);
        SharedPreferences sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", id);
        editor.putString("email", email);
        editor.putString("token", token);
        editor.apply();
        startActivity(new Intent(getApplicationContext(), DashboardStudentActivity.class));
        finish();
    }

    @Override
    public void loginError() {
        Toast.makeText(this, "Gagal masuk cek kembali Data", Toast.LENGTH_LONG).show();
    }

    @Override
    public void serverError() {
        Toast.makeText(this, "Gagal Menghubungi Server", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        progressDialog.setMessage("Masuk...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void emailValidation() {
        Toast.makeText(this, "Email Tidak Valid", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveUserData() {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

//    public void session(String response) {
//        // if user already login
//        if (preferences.teacherSession()) {
//           startActivity(new Intent(getApplicationContext(), DashboardTeacherActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
//            finish();
//            Log.d("msg", response);
//        } else if (preferences.parentSession()) {
//            startActivity(new Intent(getApplicationContext(), ParentActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
//            finish();
//        } else if (preferences.studentSession()) {
//            preferences.studentSession();
//            startActivity(new Intent(getApplicationContext(), StudentActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
//            finish();
//        }
//    }
}