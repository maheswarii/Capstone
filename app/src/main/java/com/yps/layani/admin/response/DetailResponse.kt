package com.yps.layani.admin.response

import com.google.gson.annotations.SerializedName
import com.yps.layani.admin.model.Complaint

class DetailResponse (
    @SerializedName("note")
    val note: String
)