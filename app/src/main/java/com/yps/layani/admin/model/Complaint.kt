package com.yps.layani.admin.model

import com.google.gson.annotations.SerializedName

data class Complaint (
    @SerializedName("username")
    val username: String,
    @SerializedName("tweet")
    val tweet: String,
        )