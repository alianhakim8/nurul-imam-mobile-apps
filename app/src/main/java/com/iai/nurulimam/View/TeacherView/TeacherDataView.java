package com.iai.nurulimam.View.TeacherView;

public interface TeacherDataView {

    void dataValidation();

    void showProgressDialog();

    void hideProgressDialog();

    void success();

    void failed();

    void serverError();
}
