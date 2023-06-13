package com.capstone.sofitapp.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class RecommendationResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

@Parcelize
data class DataItem(

	@field:SerializedName("cat_id")
	val catId: Int? = null,

	@field:SerializedName("exercise")
	val exercise: String? = null,

	@field:SerializedName("food")
	val food: String? = null

) : Parcelable
