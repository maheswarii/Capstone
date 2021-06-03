package com.yps.layani.admin.response

import com.google.gson.annotations.SerializedName
import com.yps.layani.admin.model.User

data class UserResponse (
    @SerializedName("user")
    val data: User
)
