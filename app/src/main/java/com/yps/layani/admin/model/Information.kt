package com.yps.layani.admin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Information(
    var title: String = "",
    var photo: String = "",
    var content_detail_info: String = ""

) : Parcelable