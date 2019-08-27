package com.mgoldsum.dash.network

import com.mgoldsum.dash.model.RestaurantModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantService {
    @GET("v2/restaurant/")
    fun getRestaurants(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<ArrayList<RestaurantModel>>
}