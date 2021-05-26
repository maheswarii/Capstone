package com.yps.layani.admin.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Notifications(
    var pesan: String = ""
) : Parcelable