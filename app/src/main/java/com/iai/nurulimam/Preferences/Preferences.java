package com.iai.nurulimam.Preferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    public static final String SP_NURUL_IMAM = "spNurulImam";
    public static final String SP_NAME = "spName";
    public static final String PARENT_SESSION = "sessonOrtu";
    public static final String TEACHER_SESSION = "sessionGuru";
    public static final String STUDENT_SESSION = "sessionSiswa";
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public Preferences(Context context) {
        sp = context.getSharedPreferences(SP_NURUL_IMAM, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void saveSPString(String keySP, String value) {
        editor.putString(keySP, value);
        editor.apply();
    }

    public void saveSPInt(String keySP, int value) {
        editor.putInt(keySP, value);
        editor.apply();
    }

    public void saveSPBoolean(String keySP, boolean value) {
        editor.putBoolean(keySP, value);
        editor.apply();
    }

    public String getName(String KeySp) {

        return KeySp;
    }

    public String getSpName() {
        return sp.getString(SP_NAME, "");
    }

    public Boolean parentSession() {
        return sp.getBoolean(PARENT_SESSION, false);
    }

    public Boolean teacherSession() {
        return sp.getBoolean(TEACHER_SESSION, false);
    }

    public Boolean studentSession() {
        return sp.getBoolean(STUDENT_SESSION, false);
    }

}
