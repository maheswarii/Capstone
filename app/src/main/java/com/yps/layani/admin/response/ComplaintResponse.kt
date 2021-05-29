package com.yps.layani.admin.response

import com.google.gson.annotations.SerializedName
import com.yps.layani.admin.model.Complaint

data class ComplaintResponse(
    @SerializedName("token")
    val token: String,

    @SerializedName("complaints")
    val complaints: List<Complaint>
)