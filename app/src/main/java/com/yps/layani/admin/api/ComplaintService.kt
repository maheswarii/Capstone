package com.yps.layani.admin.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ComplaintService {
    val depRetrofit = Retrofit.Builder()
        .baseUrl("http://localhost:8000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ListApi::class.java)
}