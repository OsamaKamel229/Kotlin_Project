package com.kotlincourse.foodapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlincourse.foodapp.R
import com.kotlincourse.foodapp.model.RecipeData

class RecipeAdapter (val c:Context, val recipeList:ArrayList<RecipeData>): RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>(){

    inner class RecipeViewHolder(val v: View):RecyclerView.ViewHolder(v){
        val recipeName = v.findViewById<TextView>(R.id.recipeName)
        val recipeIngredients = v.findViewById<TextView>(R.id.ingredients)
        val recipeInstruction = v.findViewById<TextView>(R.id.instruction)
        val recipeImage = v.findViewById<ImageView>(R.id.recipeImage)
        val recipeRate = v.findViewById<RatingBar>(R.id.rateTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_item,parent,false)
        return RecipeViewHolder(v)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val newList = recipeList[position]
        holder.recipeName.text = newList.recipeTitle
        holder.recipeIngredients.text = newList.recipeIngredients
        holder.recipeInstruction.text = newList.recipeInstruction
        holder.recipeRate.rating = newList.recipeRate
        holder.recipeImage.setImageDrawable(newList.recipeImageView.drawable)
    }
}