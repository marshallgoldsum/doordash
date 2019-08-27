package com.mgoldsum.dash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mgoldsum.dash.model.RestaurantModel
import com.mgoldsum.dash.network.RestaurantService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DashRepository @Inject constructor(val restaurantService: RestaurantService) {

    private val restaurants:MutableLiveData<ArrayList<RestaurantModel>> = MutableLiveData()

    fun fetchRestaurants(lat:Double, lng:Double, offset:Int, limit:Int):LiveData<ArrayList<RestaurantModel>>{
        restaurantService.getRestaurants(lat, lng, offset, limit).enqueue(object : Callback<ArrayList<RestaurantModel>> {
            override fun onResponse(call: Call<ArrayList<RestaurantModel>>, response: Response<ArrayList<RestaurantModel>>) {
                restaurants.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<RestaurantModel>>, t: Throwable) {
                restaurants.value = ArrayList()
            }
        })
        return restaurants
    }
}