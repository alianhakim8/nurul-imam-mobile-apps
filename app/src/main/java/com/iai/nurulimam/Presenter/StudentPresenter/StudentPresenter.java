package com.iai.nurulimam.Presenter.StudentPresenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.iai.nurulimam.API.ApiClient;
import com.iai.nurulimam.Activity.LoginActivity;
import com.iai.nurulimam.Interface.ApiInterface;
import com.iai.nurulimam.Model.UserResponse;
import com.iai.nurulimam.Preferences.Preferences;
import com.iai.nurulimam.View.StudentView.StudentView;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentPresenter {
    StudentView view;
    private Preferences preferences;
    private Context context;

    public StudentPresenter(Preferences preferences, StudentView studentView, Context context) {
        this.preferences = preferences;
        this.view = studentView;
        this.context = context;
    }

    public void logout() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setTitle("Keluar ?");
        sweetAlertDialog.setCancelText("Tidak");
        sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
            }
        }).setConfirmText("Ya")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        preferences.saveSPBoolean(Preferences.STUDENT_SESSION, false);
                        context.getApplicationContext().startActivity(new Intent(context.getApplicationContext(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }
                }).show();
    }

    public void feed(String token) {
        view.loading();
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<List<UserResponse>> call = apiInterface.getTeacherFeed("Bearer " + token);
        call.enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                if (response.isSuccessful()) {
                    List<UserResponse> userResponses = response.body();
                    view.hideLoading();
                    view.showFeed(userResponses);
                } else {
                    view.hideLoading();
                    view.error();
                    Log.i("error", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {
                view.hideLoading();
                view.serverError();
                Log.i("server error", t.getMessage());
            }
        });
    }
}
