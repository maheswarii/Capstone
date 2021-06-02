package com.yps.layani.admin.response

import com.google.gson.annotations.SerializedName
import com.yps.layani.admin.model.User

data class StatsResponse (
    @SerializedName("users")
    val users: List<User>
    )