package com.capstone.sofitapp.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.sofitapp.data.model.UserPreference
import com.capstone.sofitapp.data.response.PredictResponse
import com.capstone.sofitapp.data.response.RecommendationResponse
import kotlinx.coroutines.launch

class ResultViewModel(private val repo: UserPreference) : ViewModel() {

    val predictResponse: LiveData<PredictResponse> = repo.predictResponse
    val recommendationResponse: LiveData<RecommendationResponse> = repo.recommendationResponse

    fun doPredict(gender: String, height: String, weight: String) {
        viewModelScope.launch {
            repo.postPredict(gender, height, weight)
        }
    }

    fun doRecommendation(recommendation: Int) {
        viewModelScope.launch {
            repo.postRecommendation(recommendation)
        }
    }

}