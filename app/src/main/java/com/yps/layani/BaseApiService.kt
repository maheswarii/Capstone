package com.yps.layani

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface BaseApiService {
    @FormUrlEncoded
    @POST("api/login")
    fun loginRequest(
        @Field("email") email: String?,
        @Field("password") password: String?
    ): Call<ResponseBody?>?

    @FormUrlEncoded
    @POST("api/login")
    fun registerRequest(
        @Field("nama") nama: String?,
        @Field("email") email: String?,
        @Field("password") password: String?
    ): Call<ResponseBody?>?
}