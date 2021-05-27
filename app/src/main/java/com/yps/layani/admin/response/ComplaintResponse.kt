package com.yps.layani.admin.response

import com.google.gson.annotations.SerializedName
import com.yps.layani.admin.model.Complaint

data class ComplaintResponse (
    @SerializedName("username")
    val username: String,
    @SerializedName("complaint")
    val complaint: String,
    @SerializedName("items")
    val item: List<Complaint>
)