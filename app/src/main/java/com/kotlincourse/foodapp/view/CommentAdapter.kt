package com.kotlincourse.foodapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlincourse.foodapp.R
import com.kotlincourse.foodapp.model.CommentData

class CommentAdapter (val c:Context, val commentList:ArrayList<CommentData>):
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){

    inner class CommentViewHolder(val v: View):RecyclerView.ViewHolder(v){
        val comment = v.findViewById<TextView>(R.id.commentTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_comment, parent, false)
        return CommentViewHolder(v)
    }

    override fun getItemCount(): Int {
       return commentList.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val newList = commentList[position]
        holder.comment.text = newList.comment
    }
}