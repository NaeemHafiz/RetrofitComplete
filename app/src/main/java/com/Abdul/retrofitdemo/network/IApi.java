package com.Abdul.retrofitdemo.network;

import com.Abdul.retrofitdemo.models.GetEmpResponse;
import com.Abdul.retrofitdemo.models.LoginUser;
import com.Abdul.retrofitdemo.models.RegisterUserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static com.Abdul.retrofitdemo.utils.Constants.LOGIN;
import static com.Abdul.retrofitdemo.utils.Constants.SIGNUP;

public interface IApi {

    @FormUrlEncoded
    @POST(LOGIN)
    Call<LoginUser> loginUser(@Field("email") String email, @Field("password") String Password);

    @FormUrlEncoded
    @POST(SIGNUP)
    Call<RegisterUserResponse> signupUser(@Field("name")String name, @Field("email") String email, @Field("password") String password , @Field("password_confirmation") String confirm_password);


    @GET("users/?per_page=12&amp;page=1")
    Call<GetEmpResponse> getEmployees();
}
