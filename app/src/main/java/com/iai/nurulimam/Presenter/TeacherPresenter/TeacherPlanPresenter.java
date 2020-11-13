package com.iai.nurulimam.Presenter.TeacherPresenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.iai.nurulimam.API.ApiClient;
import com.iai.nurulimam.Interface.ApiInterface;
import com.iai.nurulimam.Model.JsonResponse;
import com.iai.nurulimam.View.TeacherView.TeacherPlanView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherPlanPresenter {
    TeacherPlanView view;
    Context context;

    public TeacherPlanPresenter(TeacherPlanView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void addData(int id, int subject, int studentClass, String semester, String years,
                        String basicCompetency, String purpose, String theory, String think, String model,
                        String toolsMedia, String learningSource, String assessment, String meeting, String timeAllocation, String date) {
        view.showProgressDialog();
        if (TextUtils.isEmpty(years) ||
                TextUtils.isEmpty(semester) ||
                TextUtils.isEmpty(basicCompetency) ||
                TextUtils.isEmpty(purpose) ||
                TextUtils.isEmpty(theory) ||
                TextUtils.isEmpty(think) ||
                TextUtils.isEmpty(model) ||
                TextUtils.isEmpty(toolsMedia) ||
                TextUtils.isEmpty(learningSource) ||
                TextUtils.isEmpty(assessment)) {
            view.hideProgressDialog();
            view.dataValidation();
        } else {
            ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
            Call<JsonResponse> call = apiInterface.teacherPlanning(id, subject, studentClass, semester, years, basicCompetency, purpose, theory, think, model, toolsMedia, learningSource, assessment, meeting, timeAllocation, date);
            call.enqueue(new Callback<JsonResponse>() {
                @Override
                public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                    if (response.isSuccessful()) {
                        view.hideProgressDialog();
                        view.success();
                    } else {
                        view.hideProgressDialog();
                        view.error();
                        Log.i("error", response.message());
                    }
                }

                @Override
                public void onFailure(Call<JsonResponse> call, Throwable t) {
                    view.hideProgressDialog();
                    view.serverError();
                }
            });
        }

    }
}
