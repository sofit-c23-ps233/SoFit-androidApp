package com.capstone.sofitapp.data.retrofit

//import com.capstone.sofitapp.data.response.Data
import com.capstone.sofitapp.data.response.LoginResponse
import com.capstone.sofitapp.data.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun doLogin(
        @Field("email") email: String,
        @Field("password") password : String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    fun doRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

//    @GET("/user")
//    fun getProfile(
//        @Field("id") id: String
//    ): Call<Data>
//
//    @PUT("/user/update")
//    fun updateProfile(
//        @Field("id_user") id_user: String,
//        @Field("username") username: String,
//        @Field("email") email: String,
//        @Field("password") password: String
//    ): Call<Data>
//
//    @DELETE("/user/delete")
//    fun deleteProfile(
//        @Field("id_user") id_user: String
//    ): Call<Data>
}