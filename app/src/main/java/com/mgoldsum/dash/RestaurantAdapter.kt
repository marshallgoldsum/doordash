package com.mgoldsum.dash

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mgoldsum.dash.model.RestaurantModel

class RestaurantAdapter(
    private val context: Context,
    private val dataSource: ArrayList<RestaurantModel>,
    private val model: DashViewModel
) : RecyclerView.Adapter<RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(LayoutInflater.from(context).inflate(R.layout.restaurant_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurantModel = dataSource[position]
        holder.name.text = restaurantModel.name
        holder.description.text = restaurantModel.description
        holder.status.text = restaurantModel.status
        holder.favorite.text = restaurantModel.favorite
        Glide.with(holder.itemView)
            .load(restaurantModel.cover_img_url)
            .fitCenter()
            .into(holder.image)
        holder.favorite.setOnClickListener {
            model.toggleFavorite(restaurantModel.id)
            restaurantModel.toggleFavorite()
            holder.favorite.text = restaurantModel.favorite
        }
    }

    override fun getItemId(position: Int): Long {
        return dataSource[position].id.toLong()
    }
}