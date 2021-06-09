package com.yps.layani.admin.response

import com.yps.layani.admin.model.User

class RegisterResponse(
    val userRegister: User,
    val message: String,
    val errors: ErrorResponse
)
class ErrorResponse(
    val email: List<String>
)
