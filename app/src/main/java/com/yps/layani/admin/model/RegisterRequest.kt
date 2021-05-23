package com.yps.layani.admin.model

import com.google.gson.annotations.SerializedName

class RegisterRequest (
    @SerializedName("email") var email: String,
    @SerializedName("fullName") var name: String,
    @SerializedName("password") var password: String)