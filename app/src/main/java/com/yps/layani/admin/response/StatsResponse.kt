package com.yps.layani.admin.response

import com.google.gson.annotations.SerializedName
import com.yps.layani.admin.model.Complaint
import com.yps.layani.admin.model.Stats

data class StatsResponse (
    @SerializedName("users")
    val users: List<Stats>
    )