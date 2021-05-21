package com.yps.layani.admin.response

import com.yps.layani.admin.model.User

class RegisterResponse(
    val status: Boolean,
    val message:String,
    val data: User
)
