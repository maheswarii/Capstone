package com.yps.layani.admin.response

import com.google.gson.annotations.Expose
import com.yps.layani.admin.model.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    @Expose
    val token: String,
    @SerializedName("status")
    @Expose
    val status: String)