// Meal.kt
package com.example.rndmealrecipe

import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("idMeal") val idMeal: String,
    @SerializedName("strMeal") val strMeal: String,
    @SerializedName("strCategory") val strCategory: String,
    @SerializedName("strArea") val strArea: String,
    @SerializedName("strInstructions") val strInstructions: String,
    @SerializedName("strMealThumb") val strMealThumb: String,
    @SerializedName("strIngredient1") val strIngredient1: String,
    @SerializedName("strIngredient2") val strIngredient2: String,
    // Add other properties as needed for other ingredients, etc.
) {
    fun getIngredient(index: Int): String {
        return when (index) {
            1 -> strIngredient1
            2 -> strIngredient2
            // Add other cases for other ingredients
            else -> ""
        }
    }

    // If measures are not provided in JSON, return an empty string
    fun getMeasure(index: Int): String {
        return ""
    }
}
