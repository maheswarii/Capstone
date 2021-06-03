package com.yps.layani.admin.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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

): Parcelable

