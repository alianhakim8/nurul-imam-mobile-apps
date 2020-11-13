package com.iai.nurulimam.View.StudentView;

import com.iai.nurulimam.Model.UserResponse;

import java.util.List;

public interface StudentView {

    void loading();

    void error();

    void hideLoading();

    void showFeed(List<UserResponse> response);

    void serverError();

}
