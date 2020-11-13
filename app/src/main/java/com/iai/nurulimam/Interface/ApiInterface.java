package com.iai.nurulimam.Interface;

import com.iai.nurulimam.Model.JsonResponse;
import com.iai.nurulimam.Model.LoginRequest;
import com.iai.nurulimam.Model.LoginResponse;
import com.iai.nurulimam.Model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("v1/data/login")
    Call<LoginResponse> userlogin(@Body LoginRequest loginRequest);

    //    // login
//    @FormUrlEncoded
//    @POST("v1/data/login")
//    Call<LoginResponse> login(
//            @Field("email") String email,
//            @Field("password") String password,
//            @Field("status_id") int status
//    );
//
//    @GET("v1/data/user/")
//    Call<Users> getData();
//
//    @FormUrlEncoded
//    @POST("v1/data/teacher/store")
//    Call<JsonResponse> teacherData(
//            @Field("first_name") String firsName,
//            @Field("last_name") String lastName,
//            @Field("date") String date,
//            @Field("address") String address,
//            @Field("program") int programStudy
//    );
//
    @FormUrlEncoded
    @POST("v1/data/rpp/store")
    Call<JsonResponse> teacherPlanning(
            @Field("user_id") int id,
            @Field("study_id") int study,
            @Field("class_id") int studyClass,
            @Field("semester") String semester,
            @Field("tahun_ajaran") String years_study,
            @Field("kompentensi_dasar") String competence,
            @Field("tujuan") String purpose,
            @Field("materi") String theory,
            @Field("pendekatan") String think,
            @Field("model") String model,
            @Field("alat_media") String media_tools,
            @Field("sumber_belajar") String learningSource,
            @Field("penilaian") String assessment,
            @Field("pertemuan") String meeting,
            @Field("alokasi_waktu") String timeAllocation,
            @Field("tanggal") String date
    );

    @FormUrlEncoded
    @POST("v1/data/posting/store")
    Call<JsonResponse> teacherPost(
            @Header("Authorization") String token,
            @Field("user_id") int id,
            @Field("title") String title,
            @Field("content") String message
    );

    @GET("v1/data/posting/user")
    Call<List<UserResponse>> getTeacherFeed(
            @Header("Authorization") String token
    );

}
