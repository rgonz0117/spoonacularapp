package com.example.spoonacularapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.spoonacularapp.adapters.RecipeAdapter
import com.example.spoonacularapp.network.RetrofitClient
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StartFromScratchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_from_scratch)

        val ingredientInput = findViewById<TextInputEditText>(R.id.ingredientInput)
        val searchButton = findViewById<MaterialButton>(R.id.searchButton)
        val recyclerView = findViewById<RecyclerView>(R.id.recipeRecyclerView)

        searchButton.setOnClickListener {
            val ingredients = ingredientInput.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = RetrofitClient.api.getRecipesByIngredients(ingredients)
                    if (response.isSuccessful) {
                        val recipes = response.body() ?: emptyList()
                        runOnUiThread {
                            recyclerView.adapter = RecipeAdapter(recipes)
                        }
                    }
                } catch (e: Exception) {
                    Log.e("StartFromScratch", "Error: ${e.localizedMessage}")
                }
            }
        }
    }
}