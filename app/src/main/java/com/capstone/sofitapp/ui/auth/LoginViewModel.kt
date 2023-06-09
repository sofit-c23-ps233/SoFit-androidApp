package com.capstone.sofitapp.ui.auth

import androidx.lifecycle.*
import com.capstone.sofitapp.data.model.User
import com.capstone.sofitapp.data.model.UserPreference
import com.capstone.sofitapp.data.response.LoginResponse
import com.capstone.sofitapp.data.utils.Event
import kotlinx.coroutines.launch


class LoginViewModel(private val repo: UserPreference) : ViewModel() {

    val loginResponse: LiveData<LoginResponse> = repo.loginResponse
    val isLoading: LiveData<Boolean> = repo.isLoading
    val toastText: LiveData<Event<String>> = repo.toastText

    fun doLogin(email: String, password: String) {
        viewModelScope.launch {
            repo.postLogin(email, password)
        }
    }

    fun saveSession(session: User) {
        viewModelScope.launch {
            repo.saveSession(session)
        }
    }

    fun login() {
        viewModelScope.launch {
            repo.login()
        }
    }

    fun saveId(id: String) {
        viewModelScope.launch {
            repo.saveId(id)
        }
    }
}