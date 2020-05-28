package com.iai.nurulimammobileapps.activity.model;

public class Guru {

    private String email, nama, nik, tmt, password, status;

    public Guru() {
    }

    public Guru(String email, String nama, String nik, String tmt, String password, String status) {
        this.email = email;
        this.nama = nama;
        this.nik = nik;
        this.tmt = tmt;
        this.password = password;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public String getNama() {
        return nama;
    }

    public String getNik() {
        return nik;
    }

    public String getTmt() {
        return tmt;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }
}
