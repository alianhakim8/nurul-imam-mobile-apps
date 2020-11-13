package com.iai.nurulimam.View;

public interface LoginView {

    void loginValidation();

    void teacherLogin(int id, String email, String token);

    void parentLogin();

    void studentLogin(int id, String email, String token);

    void loginError();

    void serverError();

    void showProgress();

    void hideProgress();

    void emailValidation();

    void saveUserData();

}
