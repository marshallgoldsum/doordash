package com.mgoldsum.dash.model

data class RestaurantModel(
    val id: Int,
    val name: String,
    val description: String,
    val cover_img_url: String,
    val status: String,
    val delivery_fee: String,
    var favorite: String = "Not Favorite"
) {
    fun toggleFavorite(){
        if (favorite == "Not Favorite") {
            favorite = "Favorite"
        } else {
            favorite = "Not Favorite"
        }
    }
}
