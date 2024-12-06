package com.example.spoonacularapp.models

data class Recipe(
    val id: Int,
    val title: String,
    val image: String,
    val readyInMinutes: Int
)

data class RecipeResponse(
    val results: List<Recipe>
)