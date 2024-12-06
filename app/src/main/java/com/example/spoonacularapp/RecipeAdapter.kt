package com.example.spoonacularapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spoonacularapp.R
import com.example.spoonacularapp.models.Recipe

class RecipeAdapter(private val recipes: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeImage: ImageView = itemView.findViewById(R.id.recipeImageView)
        val recipeTitle: TextView = itemView.findViewById(R.id.recipeTitleTextView)
        val recipeTime: TextView = itemView.findViewById(R.id.recipeTimeTextView)
        val recipeDifficulty: TextView = itemView.findViewById(R.id.recipeDifficultyTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]

        holder.recipeTitle.text = recipe.title
        holder.recipeTime.text = "Time: ${recipe.readyInMinutes} mins"

        // Calculate difficulty based on cooking time
        val difficulty = when {
            recipe.readyInMinutes <= 20 -> "Easy"
            recipe.readyInMinutes <= 40 -> "Medium"
            else -> "Hard"
        }
        holder.recipeDifficulty.text = "Difficulty: $difficulty"

        // Load the recipe image using Glide
        Glide.with(holder.itemView.context)
            .load(recipe.image)
            .placeholder(R.drawable.placeholder_image) // Add a placeholder image
            .into(holder.recipeImage)
    }

    override fun getItemCount(): Int = recipes.size
}