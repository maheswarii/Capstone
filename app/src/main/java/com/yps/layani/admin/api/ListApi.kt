package com.yps.layani.admin.api

import com.yps.layani.admin.model.Complaint
import com.yps.layani.admin.model.DetailRequest
import com.yps.layani.admin.model.LoginRequest
import com.yps.layani.admin.model.RegisterRequest
import com.yps.layani.admin.response.*
import retrofit2.Call
import retrofit2.http.*

interface ListApi {
    //TODO : Register User
    @POST("api/register")
    fun doRegister(@Body signupRequest: RegisterRequest): Call<RegisterResponse> // body data

    //TODO : Login User
    @POST("api/login")
    fun doLogin(@Body signinRequest: LoginRequest): Call<LoginResponse> // body data

    //TODO : Get User
    @GET("api/user")
    fun getUserProfile(@Header("Authorization") token: String): Call<UserResponse>

    //TODO : Get Complaint List
    @GET("api/complaint/index")
    fun getListComplaint(@Header("Authorization") token: String): Call<ComplaintResponse>

    //TODO : Get Leaderboard List
    @GET("api/leaderboard")
    fun getListLeaderboard(@Header("Authorization") token: String): Call<StatsResponse>

    //TODO : Detail Complaint
    @POST("complaint/1/accept")
    fun doSolved(@Body solvedRequest: DetailRequest): Call<DetailResponse> // body data
}
