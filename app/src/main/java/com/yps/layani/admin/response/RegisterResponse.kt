package com.yps.layani.admin.response

import com.yps.layani.admin.model.User

class RegisterResponse(
    val user : UserResponse,
    val message:String,
    val errors: ErrorResponse
)

class ErrorResponse(
    val email: List<String>
)
