package com.yps.layani.admin.model

import com.google.gson.annotations.SerializedName

data class User (

    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("photo")
    val photo: Int,
    @SerializedName("exp")
    val exp: Int,
    @SerializedName("rank")
    val rank: String
)

