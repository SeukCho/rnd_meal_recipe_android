// MealResponse.kt
package com.example.rndmealrecipe

import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("meals") val meals: List<Meal>
)
