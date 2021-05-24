package com.yps.layani.admin.api

import com.yps.layani.admin.model.LoginRequest
import com.yps.layani.admin.model.RegisterRequest
import com.yps.layani.admin.response.LoginResponse
import com.yps.layani.admin.response.RegisterResponse
import com.yps.layani.admin.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ListApi {

    @POST("api/register")
    @Headers("Accept: application/json")
    fun doRegister(
        @Body registerRequest: RegisterRequest
    ): Call<RegisterResponse>


    @POST("api/login")
    @Headers("Accept: application/json")
    fun doLogin(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>


    @GET("get_user_detail.php")
    @Headers("Accept: application/json")
    fun getUser(@Query("id") id: String): Call<UserResponse>
}
