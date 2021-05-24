package com.yps.layani.admin.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.yps.layani.admin.model.User

data class RegisterResponse(
    @SerializedName("message")
    @Expose
    val message: String,
    @SerializedName("status")
    @Expose
    val status: Boolean
    )
