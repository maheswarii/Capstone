package com.yps.layani.admin.response

class RegisterResponse(
    val userRegister : UserResponse,
    val message:String,
    val errors: ErrorResponse
)

class ErrorResponse(
    val email: List<String>
)
