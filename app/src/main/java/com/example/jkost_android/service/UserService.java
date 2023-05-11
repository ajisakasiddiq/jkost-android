package com.example.jkost_android.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class UserService {

    @POST("login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
}
