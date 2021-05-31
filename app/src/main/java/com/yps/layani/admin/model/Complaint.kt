package com.yps.layani.admin.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Complaint (
    @SerializedName("username")
    val username: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("photo")
    val photo: Int
): Parcelable