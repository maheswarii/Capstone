package com.yps.layani.admin.model

import com.google.gson.annotations.SerializedName

data class Complaint (
    @SerializedName("username")
    val username: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("photo")
    val photo: Int
)