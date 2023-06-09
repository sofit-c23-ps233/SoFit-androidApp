package com.capstone.sofitapp.data.response

import com.google.gson.annotations.SerializedName

data class ProfileRequest(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
