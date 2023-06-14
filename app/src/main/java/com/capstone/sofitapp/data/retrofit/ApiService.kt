package com.capstone.sofitapp.data.retrofit

import com.capstone.sofitapp.data.response.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("/auth/login")
    fun doLogin(
        @Field("email") email: String,
        @Field("password") password : String
    ): Call<LoginResponse>

    @POST("/auth/register")
    fun doRegister(
        @Body registerRequest: RegisterRequest
    ): Call<RegisterResponse>

    @GET("/recommendation/{id}")
    fun doRecommendation(
        @Path("id") id: String
    ): Call<RecommendationResponse>

    @PUT("/edit-profile")
    fun doUpdate(
        @Body profileRequest: ProfileRequest
    ): Call<ProfileResponse>

//    @GET("/result/{id}")
//    fun doHistory(
//        @Path("id") id: String,
//        @Query("category") category: String
//    ): Call<HistoryResponse>
//
//    @DELETE("/delete-user/{id_user}")
//    fun deleteProfile(
//        @Path("id_user") id_user: String
//    ): Call<DeleteProfile>

}