package com.iai.nurulimam.Activity.Student;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iai.nurulimam.Adapter.StudentHomeAdapter;
import com.iai.nurulimam.Model.UserResponse;
import com.iai.nurulimam.Preferences.Preferences;
import com.iai.nurulimam.Presenter.StudentPresenter.StudentPresenter;
import com.iai.nurulimam.R;
import com.iai.nurulimam.View.StudentView.StudentView;

import java.util.List;

public class StudentFeedActivity extends AppCompatActivity implements StudentView {

    RecyclerView recyclerView;
    StudentHomeAdapter adapter;
    StudentPresenter presenter;
    ProgressDialog progressDialog;
    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_feed);
        view();
        SharedPreferences sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        presenter.feed(token);
    }

    private void view() {
        progressDialog = new ProgressDialog(this);
        presenter = new StudentPresenter(preferences, this, this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new StudentHomeAdapter();
        preferences = new Preferences(this);
    }

    @Override
    public void loading() {
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void error() {
        Toast.makeText(this, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        progressDialog.hide();
    }

    @Override
    public void showFeed(List<UserResponse> response) {
        adapter.setData(response);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void serverError() {
        Toast.makeText(this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
    }
}