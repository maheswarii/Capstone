package com.yps.layani.admin.response

import com.yps.layani.admin.model.User

data class UserResponse (
    val status: String,
    val data: User
    )