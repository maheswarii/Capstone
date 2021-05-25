package com.yps.layani.admin.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.yps.layani.admin.model.User


data class LoginResponse(
    val status: String,
    val message:String,
    val data: User
//    @SerializedName("token")
//    @Expose
//    val token: String,
//    @SerializedName("status")
//    @Expose
//    val status: String
    )