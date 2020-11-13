package com.iai.nurulimam.Model;

public class LoginRequest {

    private String email, password;
    private int status_account_id;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus_account_id() {
        return status_account_id;
    }

    public void setStatus_account_id(int status_account_id) {
        this.status_account_id = status_account_id;
    }
}
