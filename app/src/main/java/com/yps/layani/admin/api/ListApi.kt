package com.yps.layani.admin.api

import com.yps.layani.admin.model.LoginRequest
import com.yps.layani.admin.model.RegisterRequest
import com.yps.layani.admin.response.LoginResponse
import com.yps.layani.admin.response.RegisterResponse
import com.yps.layani.admin.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ListApi {
    //TODO : Register User
    @POST("api/register")
    fun doRegister(
        @Body signupRequest: RegisterRequest
    ): Call<RegisterResponse> // body data

    //TODO : Login User
    @POST("api/login")
    fun doLogin(
        @Body signinRequest: LoginRequest
    ): Call<LoginResponse> // body data

    //TODO : Get User
    @GET("api/user")
    fun getUser(@Query("id") id: String): Call<UserResponse>

    //TODO : Get Complaint List
    @GET("api/user")
    fun getListComplaint(@Query("id") id: String): Call<UserResponse>

}
