package com.capstone.sofitapp.data.response

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

	@field:SerializedName("data")
	val data: List<DataHistory?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataHistory(

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("result_date")
	val resultDate: String? = null,

	@field:SerializedName("result_id")
	val resultId: String? = null,

	@field:SerializedName("category")
	val category: String? = null
)
