package com.capstone.sofitapp.ui.history

//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.capstone.sofitapp.data.model.UserPreference
//import com.capstone.sofitapp.data.response.HistoryResponse
//import kotlinx.coroutines.launch
//
//class HistoryViewModel(private val repo: UserPreference) : ViewModel() {
//
//    val historyResponse: LiveData<HistoryResponse> = repo.historyResponse
//
//    fun doHistory(category: String, user_id: String) {
//        viewModelScope.launch {
//            repo.postHistory(category, user_id)
//        }
//    }
//}