package com.capstone.sofitapp.data.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.capstone.sofitapp.data.response.*
import com.capstone.sofitapp.data.retrofit.ApiService
import com.capstone.sofitapp.data.retrofit.ApiService2
import com.capstone.sofitapp.data.utils.Event
import com.capstone.sofitapp.data.utils.SessionPreferences
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserPreference private constructor(
    private val apiService: ApiService,
    private val apiService2: ApiService2,
    private val pref: SessionPreferences
){
    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> = _registerResponse

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    private val _predictResponse = MutableLiveData<PredictResponse>()
    val predictResponse: LiveData<PredictResponse> = _predictResponse

    private val _profileResponse = MutableLiveData<ProfileResponse>()
    val profileResponse: LiveData<ProfileResponse> = _profileResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _toastText = MutableLiveData<Event<String>>()
    val toastText: LiveData<Event<String>> = _toastText

    fun postRegister(name: String, email: String, password: String) {
        _isLoading.value = true
//        val client = apiService.doRegister(name, email, password)
        val client = apiService.doRegister(RegisterRequest(name, email, password) )

        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful && response.body() != null) {
                    _registerResponse.value = response.body()
                    _toastText.value = Event(response.body()?.message.toString())
                } else {
                    _toastText.value = Event(response.message().toString())
                    Log.e(
                        TAG,
                        "onFailure: ${response.message()}, ${response.body()?.message.toString()}"
                    )
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _toastText.value = Event(t.message.toString())
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun postLogin(email: String, password: String) {
        _isLoading.value = true
        val client = apiService.doLogin(email, password)

        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful && response.body() != null) {
                    _loginResponse.value = response.body()
                    _toastText.value = Event(response.body()?.message.toString())
                } else {
                    _toastText.value = Event(response.message().toString())
                    Log.e(
                        TAG,
                        "onFailure: ${response.message()}, ${response.body()?.message.toString()}"
                    )
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _toastText.value = Event(t.message.toString())
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun postPredict(gender: String, height: String, weight: String) {
        _isLoading.value = true
        val client = apiService2.doPredict(PredictRequest(gender, height, weight))

        client.enqueue(object : Callback<PredictResponse> {
            override fun onResponse(
                call: Call<PredictResponse>,
                response: Response<PredictResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful && response.body() != null) {
                    _predictResponse.value = response.body()
                } else {
                    _toastText.value = Event(response.message().toString())
                    Log.e(
                        TAG,
                        "onFailure: ${response.message()}, ${response.errorBody()?.string().toString()}"
                    )
                }
            }

            override fun onFailure(call: Call<PredictResponse>, t: Throwable) {
                _toastText.value = Event(t.message.toString())
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun postProfile(id: String, email: String, username: String) {
        _isLoading.value = true
        val client = apiService.doUpdate(ProfileRequest(id, email, username))

        client.enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful && response.body() != null) {
                    _profileResponse.value = response.body()
                } else {
                    _toastText.value = Event(response.message().toString())
                    Log.e(
                        TAG,
                        "onFailure: ${response.message()}, ${response.errorBody()?.string().toString()}"
                    )
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                _toastText.value = Event(t.message.toString())
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getId(): Flow<String> {
        return pref.getId()
    }

    suspend fun saveId(id: String) {
        pref.saveId(id)
    }

    fun getSession(): LiveData<User> {
        return pref.getSession().asLiveData()
    }

    suspend fun saveSession(session: User) {
        pref.saveSession(session)
    }

    suspend fun login() {
        pref.login()
    }

    suspend fun logout() {
        pref.logout()
    }

    companion object {
        private const val TAG = "UserPreference"

        @Volatile
        private var instance: UserPreference? = null
        fun getInstance(
            preferences: SessionPreferences,
            apiService: ApiService,
            apiService2: ApiService2
        ): UserPreference =
            instance ?: synchronized(this) {
                instance ?: UserPreference(apiService,apiService2, preferences)
            }.also { instance = it }
    }
}