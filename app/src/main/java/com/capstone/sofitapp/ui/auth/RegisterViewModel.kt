package com.capstone.sofitapp.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.sofitapp.data.model.UserPreference
import com.capstone.sofitapp.data.response.RegisterResponse
import com.capstone.sofitapp.data.utils.Event
import kotlinx.coroutines.launch

class RegisterViewModel(private val repo: UserPreference) : ViewModel() {

    val registerResponse: LiveData<RegisterResponse> = repo.registerResponse
    val isLoading: LiveData<Boolean> = repo.isLoading
    val toastText: LiveData<Event<String>> = repo.toastText

    fun doRegister(name: String, email: String, password: String) {
        viewModelScope.launch {
            repo.postRegister(name, email, password)
        }
    }
}