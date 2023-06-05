package com.capstone.sofitapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.sofitapp.data.model.User
import com.capstone.sofitapp.data.model.UserPreference
import com.capstone.sofitapp.data.utils.Event
import kotlinx.coroutines.launch

class MainViewModel(private val repo: UserPreference) : ViewModel() {

    val toastText: LiveData<Event<String>> = repo.toastText

    fun getSession(): LiveData<User> {
        return repo.getSession()
    }

    fun logout() {
        viewModelScope.launch {
            repo.logout()
        }
    }

}