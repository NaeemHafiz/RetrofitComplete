package com.Abdul.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.Abdul.retrofitdemo.Constants.LOGIN;

public interface IApi {

    @FormUrlEncoded
    @POST(LOGIN)
    Call<User> loginUser(@Field("email") String email, @Field("password") String Password);

}
