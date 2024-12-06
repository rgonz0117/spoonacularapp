package com.example.spoonacularapp.network

import com.example.spoonacularapp.models.Recipe
import com.example.spoonacularapp.models.RecipeResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularApi {
    @GET("recipes/random")
    suspend fun getPopularRecipes(
        @Query("number") number: Int = 10,
        @Query("apiKey") apiKey: String = "52ec9a291b824a61bc752134fab1f26b"
    ): Response<RecipeResponse>

    @GET("recipes/findByIngredients")
    suspend fun getRecipesByIngredients(
        @Query("ingredients") ingredients: String,
        @Query("apiKey") apiKey: String = "52ec9a291b824a61bc752134fab1f26b"
    ): Response<List<Recipe>>
}

object RetrofitClient {
    private const val BASE_URL = "https://api.spoonacular.com/"

    val api: SpoonacularApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpoonacularApi::class.java)
    }
}