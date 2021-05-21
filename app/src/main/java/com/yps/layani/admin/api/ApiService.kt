package com.yps.layani.admin.api

import retrofit2.Retrofit

object ApiService {
    private val TAG = "--ApiService"

    private const val BASE_URL = "https://6e2e4e945ef5.ngrok.io/"

    fun loginApiCall() = Retrofit.Builder()
        .baseUrl(BASE_URL)
      //      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(ApiWorker.gsonConverter)
        .client(ApiWorker.client)
        .build()
        .create(ListApi::class.java)!!
}