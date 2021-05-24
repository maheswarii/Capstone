package com.yps.layani.admin.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Complaint(
    var name: String = "",
    var complaint: String = ""
):Parcelable