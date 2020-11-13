package com.iai.nurulimam.View.TeacherView;

public interface TeacherListView {

    void showLoading();

    void hideLoading();

//    void getListGuru(List<TeacherListModel> listGuru);

    void onErrorLoading(String message);
}
