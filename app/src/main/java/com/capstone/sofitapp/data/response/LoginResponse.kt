package com.capstone.sofitapp.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("success")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: Data? = null
)

data class Data(

    @field:SerializedName("username")
    val name: String,

    @field:SerializedName("email")
    val email: String
)