package com.mgoldsum.dash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class DashViewModel @Inject constructor(val dashRepository: DashRepository) : ViewModel() {

    var restaurants = dashRepository.fetchRestaurants(37.422740, -122.139956, 0, 50)

    fun toggleFavorite(id:Int){
        dashRepository.toggleFavorite(id)
    }

}

class DashViewModelFactory(
    private val dashRepository: DashRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return DashViewModel(dashRepository) as T
    }
}