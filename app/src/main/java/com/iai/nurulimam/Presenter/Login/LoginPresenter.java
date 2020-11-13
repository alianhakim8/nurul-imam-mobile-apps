package com.iai.nurulimam.Presenter.Login;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import com.iai.nurulimam.API.ApiClient;
import com.iai.nurulimam.Model.LoginRequest;
import com.iai.nurulimam.Model.LoginResponse;
import com.iai.nurulimam.Preferences.Preferences;
import com.iai.nurulimam.View.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {

    private LoginView view;
    private Preferences preferences;
    private Context context;

    public LoginPresenter(LoginView loginGuruView, Context context, Preferences preferences) {
        this.view = loginGuruView;
        this.context = context;
        this.preferences = preferences;
    }

    public void login(String email, String password, int status) {
        view.showProgress();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            view.hideProgress();
            view.loginValidation();

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.hideProgress();
            view.emailValidation();
        } else {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setEmail(email);
            loginRequest.setPassword(password);
            loginRequest.setStatus_account_id(status);

            Call<LoginResponse> loginResponseCall = ApiClient.getApiInterface().userlogin(loginRequest);
            loginResponseCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        LoginResponse loginResponse = response.body();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                view.hideProgress();
                                int idUser = loginResponse.getId();
                                String email = loginResponse.getEmail();
                                String token = loginResponse.getToken();
                                if (status == 1) {
                                    view.teacherLogin(idUser, email, token);
                                } else if (status == 2) {
                                    view.parentLogin();
                                } else if (status == 3) {
                                    view.studentLogin(idUser, email, token);
                                }
                            }
                        }, 700);
                    } else {
                        view.hideProgress();
                        view.loginError();
                        Log.i("error", response.message());
                    }

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    view.hideProgress();
                    view.serverError();
                }
            });
        }


    }


}
