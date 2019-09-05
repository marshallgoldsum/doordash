package com.mgoldsum.dash

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mgoldsum.dash.model.RestaurantModel
import com.mgoldsum.dash.network.RestaurantService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class DashRepository @Inject constructor(val restaurantService: RestaurantService, val context: Context) {

    private val restaurants:MutableLiveData<ArrayList<RestaurantModel>> = MutableLiveData()
    private val favorites = mutableSetOf<String>()

    init {
        loadFavorites()
    }

    fun fetchRestaurants(lat:Double, lng:Double, offset:Int, limit:Int):LiveData<ArrayList<RestaurantModel>>{
        restaurantService.getRestaurants(lat, lng, offset, limit).enqueue(object : Callback<ArrayList<RestaurantModel>> {
            override fun onResponse(call: Call<ArrayList<RestaurantModel>>, response: Response<ArrayList<RestaurantModel>>) {
                response.body()?.forEach {
                    if (favorites.contains(it.id.toString())) {
                        it.favorite = "Favorite"
                    } else {
                        it.favorite = "Not Favorite"
                    }
                }
                restaurants.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<RestaurantModel>>, t: Throwable) {
                restaurants.value = ArrayList()
            }
        })
        return restaurants
    }

    fun toggleFavorite(id:Int){
        if (favorites.contains(id.toString())) {
            favorites.remove(id.toString())
        } else {
            favorites.add(id.toString())
        }
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("listOfFavorites")
        editor.putStringSet("listOfFavorites", favorites)
        editor.commit()
    }

    fun loadFavorites(){
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val defaultSet:Set<String> = setOf()
        val set = sharedPreferences.getStringSet("listOfFavorites", defaultSet)
        favorites.clear()
        favorites.addAll(set)
    }
}