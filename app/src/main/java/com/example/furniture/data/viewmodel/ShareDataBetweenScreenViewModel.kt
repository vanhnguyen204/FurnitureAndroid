package com.example.furniture.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class NavArgsRatingDetail(
    val productId: String,
    val image: String,
    val name: String,
)
class ShareDataBetweenScreenViewModel: ViewModel() {
     val _ratingDetailsArgs = MutableLiveData(NavArgsRatingDetail("", "", ""))


    fun setRatingDetailsArgs(navArgsRatingDetail: NavArgsRatingDetail) {
        _ratingDetailsArgs.value = navArgsRatingDetail
    }
}