package com.kotlincourse.foodapp.model

import android.widget.ImageView

data class RecipeData (
    val recipeTitle: String,
    val recipeIngredients: String,
    val recipeInstruction: String,
    val recipeImageView: ImageView,
    val recipeRate: Float
)