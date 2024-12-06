package com.example.spoonacularapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spoonacularapp.R

class FoodImageAdapter(private val foodImages: List<String>) :
    RecyclerView.Adapter<FoodImageAdapter.FoodImageViewHolder>() {

    class FoodImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImage: ImageView = itemView.findViewById(R.id.foodImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_food_image, parent, false)
        return FoodImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodImageViewHolder, position: Int) {
        val imageUrl = foodImages[position]
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder_image) // Add a placeholder image
            .into(holder.foodImage)
    }

    override fun getItemCount(): Int = foodImages.size
}