package com.kotlincourse.foodapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlincourse.foodapp.R
import com.kotlincourse.foodapp.model.BlogData

class BlogAdapter(val c:Context, val blogList:ArrayList<BlogData>):
    RecyclerView.Adapter<BlogAdapter.BlogViewHolder>(){

    inner class BlogViewHolder(val v: View):RecyclerView.ViewHolder(v){
        val userName = v.findViewById<TextView>(R.id.userNameTV)
        val postDetails = v.findViewById<TextView>(R.id.postDetailsTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_item_blogs, parent, false)
        return BlogViewHolder(v)
    }

    override fun getItemCount(): Int {
        return blogList.size
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val newList = blogList[position]
        holder.userName.text = newList.userName
        holder.postDetails.text = newList.postDetails
    }

}