package com.yps.layani.admin.response

import com.google.gson.annotations.SerializedName
import com.yps.layani.admin.model.Complaint

data class ComplaintResponse(
    @SerializedName("complaints")
    val complaints: List<Complaint>
)