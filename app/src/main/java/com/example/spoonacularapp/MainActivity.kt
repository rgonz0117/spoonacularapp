package com.example.spoonacularapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.spoonacularapp.adapters.FoodImageAdapter
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val foodImages = listOf(
            "https://via.placeholder.com/150",
            "https://via.placeholder.com/150",
            "https://via.placeholder.com/150"
        )

        val recyclerView = findViewById<RecyclerView>(R.id.foodRecyclerView)
        recyclerView.adapter = FoodImageAdapter(foodImages)

        val popularButton = findViewById<MaterialButton>(R.id.popularButton)
        popularButton.setOnClickListener {
            startActivity(Intent(this, PopularRecipesActivity::class.java))
        }

        val startScratchButton = findViewById<MaterialButton>(R.id.startScratchButton)
        startScratchButton.setOnClickListener {
            startActivity(Intent(this, StartFromScratchActivity::class.java))
        }
    }
}