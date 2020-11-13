package com.iai.nurulimam.Presenter.ParentPresenter;

import android.content.Context;
import android.content.Intent;

import com.iai.nurulimam.Activity.LoginActivity;
import com.iai.nurulimam.Preferences.Preferences;
import com.iai.nurulimam.View.ParentView.ParentView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ParentPresenter {
    ParentView view;
    private Preferences preferences;
    private Context context;

    public ParentPresenter(Preferences preferences, ParentView parentView, Context context) {
        this.preferences = preferences;
        this.view = parentView;
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
                        preferences.saveSPBoolean(Preferences.PARENT_SESSION, false);
                        context.getApplicationContext().startActivity(new Intent(context.getApplicationContext(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }
                }).show();
    }
}
