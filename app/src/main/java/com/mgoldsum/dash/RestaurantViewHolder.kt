package com.mgoldsum.dash

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val image: ImageView = itemView.findViewById(R.id.restaurant_image)
    val name: TextView = itemView.findViewById(R.id.name)
    val status: TextView = itemView.findViewById(R.id.status)
    val description: TextView = itemView.findViewById(R.id.description)
    val favorite: Button = itemView.findViewById(R.id.favorite_button)

}