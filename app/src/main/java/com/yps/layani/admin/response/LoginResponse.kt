package com.yps.layani.admin.response


data class LoginResponse(
    val status: String,
    val token:String,
    val data: UserResponse
//    @SerializedName("token")
//    @Expose
//    val token: String,
//    @SerializedName("status")
//    @Expose
//    val status: String
    )
