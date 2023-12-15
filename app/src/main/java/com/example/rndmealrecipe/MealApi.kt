// MealApi.kt
package com.example.rndmealrecipe

import retrofit2.Call
import retrofit2.http.GET

interface MealApi {
    @GET("random.php")
    fun getRandomMeal(): Call<MealResponse>
}
