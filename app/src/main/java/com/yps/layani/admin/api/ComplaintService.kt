package com.yps.layani.admin.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ComplaintService {
    fun create(): ListApi {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://layani-app.et.r.appspot.com/")
            .client(ApiWorker.client)
            .build()
        return retrofit.create(ListApi::class.java)
    }
}