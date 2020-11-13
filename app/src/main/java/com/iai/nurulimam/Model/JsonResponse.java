package com.iai.nurulimam.Model;

import androidx.annotation.NonNull;

public class JsonResponse {
    private String response;


    public JsonResponse(String response) {
        this.response = response;

    }


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @NonNull
    @Override
    public String toString() {
        return "JsonResponse{" +
                "response'" + response + '\'' +
                '}';
    }
}
