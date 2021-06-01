package com.yps.layani.admin.model

import com.google.gson.annotations.SerializedName

class LoginRequest(
    @SerializedName
        ("email")
    var email: String,
    @SerializedName
        ("password")
    var password: String
)

