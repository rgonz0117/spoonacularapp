package com.example.spoonacularapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.spoonacularapp.adapters.RecipeAdapter
import com.example.spoonacularapp.models.Recipe
import com.example.spoonacularapp.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PopularRecipesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_recipes)

        val recyclerView = findViewById<RecyclerView>(R.id.recipeRecyclerView)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.api.getPopularRecipes()
                if (response.isSuccessful) {
                    val recipes = response.body()?.results ?: emptyList()
                    runOnUiThread {
                        recyclerView.adapter = RecipeAdapter(recipes)
                    }
                }
            } catch (e: Exception) {
                Log.e("PopularRecipesActivity", "Error: ${e.localizedMessage}")
            }
        }
    }
}