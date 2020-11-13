package com.iai.nurulimam.Presenter.TeacherPresenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.iai.nurulimam.API.ApiClient;
import com.iai.nurulimam.Interface.ApiInterface;
import com.iai.nurulimam.Model.JsonResponse;
import com.iai.nurulimam.View.TeacherView.TeacherPostView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherPostPresenter {
    private TeacherPostView view;
    private Context context;

    public TeacherPostPresenter(TeacherPostView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void post(int id, String title, String postMessage, String token) {
        view.loading();
        if (TextUtils.isEmpty(postMessage) || TextUtils.isEmpty(title)) {
            view.hideLoading();
            view.validation();
        } else {
            view.loading();
            ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
            Call<JsonResponse> call = apiInterface.teacherPost("Bearer" + token, id, title, postMessage);
            call.enqueue(new Callback<JsonResponse>() {
                @Override
                public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                    if (response.isSuccessful()) {
                        view.hideLoading();
                        view.success();
                    } else {
                        view.hideLoading();
                        view.error();
                        Log.i("gagal", response.message());
                    }
                }

                @Override
                public void onFailure(Call<JsonResponse> call, Throwable t) {
                    view.hideLoading();
                    view.serverError();
                    Log.i("error", t.getMessage());
                }
            });
        }
    }
}
