package com.yps.layani.admin.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(
    var fullName: String = "",
    var email: String = ""
) : Parcelable

