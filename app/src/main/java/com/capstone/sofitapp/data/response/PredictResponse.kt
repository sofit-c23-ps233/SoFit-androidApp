package com.capstone.sofitapp.data.response

import com.google.gson.annotations.SerializedName

data class PredictResponse(

	@field:SerializedName("category")
	val category: String? = null
)
