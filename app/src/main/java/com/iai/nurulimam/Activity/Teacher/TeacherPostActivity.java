package com.iai.nurulimam.Activity.Teacher;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.iai.nurulimam.Presenter.TeacherPresenter.TeacherPostPresenter;
import com.iai.nurulimam.R;
import com.iai.nurulimam.View.TeacherView.TeacherPostView;

public class TeacherPostActivity extends AppCompatActivity implements TeacherPostView {

    ProgressDialog progressDialog;
    TextInputLayout title, post;
    TeacherPostPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_post);

        // ui reference
        view();
    }

    private void view() {
        title = findViewById(R.id.titlePost);
        progressDialog = new ProgressDialog(this);
        post = findViewById(R.id.postMessage);
        presenter = new TeacherPostPresenter(this, this);
    }

    // button onClick
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnPost:
                SharedPreferences sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
                int user_id = sharedPreferences.getInt("id", 0);
                String token = sharedPreferences.getString("token", "");
                String titlePost = title.getEditText().getText().toString().trim();
                String postUser = post.getEditText().getText().toString().trim();
                presenter.post(user_id, titlePost, postUser, token);
                break;
        }
    }

    @Override
    public void loading() {
        progressDialog.setMessage("Posting...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void success() {
        Toast.makeText(this, "Berhasil memposting", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error() {
        Toast.makeText(this, "Gagal memposting", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void serverError() {
        Toast.makeText(this, "Server Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        progressDialog.hide();
    }

    @Override
    public void validation() {
        Toast.makeText(this, "Tidak Bisa Kosong", Toast.LENGTH_SHORT).show();
    }
}