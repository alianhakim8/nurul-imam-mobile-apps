package com.iai.nurulimam.View.TeacherView;

public interface TeacherPlanView {

    void showProgressDialog();

    void hideProgressDialog();

    void error();

    void success();

    void dataValidation();

    void serverError();

    void clearEditText();

}
