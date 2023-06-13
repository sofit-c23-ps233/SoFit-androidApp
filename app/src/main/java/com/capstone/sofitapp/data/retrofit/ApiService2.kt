package com.capstone.sofitapp.data.retrofit

import com.capstone.sofitapp.data.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService2 {

    @POST("/predict")
    fun doPredict(
        @Body predictRequest: PredictRequest
    ): Call<PredictResponse>

}