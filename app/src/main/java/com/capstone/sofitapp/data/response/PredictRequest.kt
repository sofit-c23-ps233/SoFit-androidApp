package com.capstone.sofitapp.data.response

import com.google.gson.annotations.SerializedName

data class PredictRequest(

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("height")
	val height: String? = null,

	@field:SerializedName("weight")
	val weight: String? = null

)
