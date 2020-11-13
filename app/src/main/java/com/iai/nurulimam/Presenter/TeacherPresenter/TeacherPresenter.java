package com.iai.nurulimam.Presenter.TeacherPresenter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;

import com.iai.nurulimam.Activity.LoginActivity;
import com.iai.nurulimam.Preferences.Preferences;
import com.iai.nurulimam.View.TeacherView.TeacherView;

public class TeacherPresenter {

    TeacherView view;
    private Preferences preferences;
    private Context context;

    public TeacherPresenter(Preferences preferences, TeacherView guruView, Context context) {
        this.preferences = preferences;
        this.view = guruView;
        this.context = context;
    }

    public void logout() {

        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle("Keluar ?")
                .setMessage("Jika anda keluar anda harus masuk kembali")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        preferences.saveSPBoolean(Preferences.TEACHER_SESSION, false);
                        context.getApplicationContext().startActivity(new Intent(context.getApplicationContext(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

        alertDialog.getButton(Dialog.BUTTON_POSITIVE).setBackgroundColor(Color.TRANSPARENT);
        alertDialog.getButton(Dialog.BUTTON_POSITIVE).setTextColor(Color.rgb(255, 109, 0));
        alertDialog.getButton(Dialog.BUTTON_NEGATIVE).setBackgroundColor(Color.TRANSPARENT);
        alertDialog.getButton(Dialog.BUTTON_NEGATIVE).setTextColor(Color.rgb(255, 109, 0));

        alertDialog.show();

    }


}
