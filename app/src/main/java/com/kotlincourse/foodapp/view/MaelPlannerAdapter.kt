package com.kotlincourse.foodapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlincourse.foodapp.R
import com.kotlincourse.foodapp.model.MaelPlannerData

class MaelPlannerAdapter (val c:Context, val maelPlannerList:ArrayList<MaelPlannerData>):
    RecyclerView.Adapter<MaelPlannerAdapter.MaelPlannerViewHolder>() {

    inner class MaelPlannerViewHolder(val v: View):RecyclerView.ViewHolder(v){
        val mealDay = v.findViewById<TextView>(R.id.maelDayTV)
        val maelName = v.findViewById<TextView>(R.id.maelNameTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaelPlannerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list_item_mael_planner, parent, false)
        return MaelPlannerViewHolder(v)
    }

    override fun getItemCount(): Int {
       return maelPlannerList.size
    }

    override fun onBindViewHolder(holder: MaelPlannerViewHolder, position: Int) {
        val newList = maelPlannerList[position]
        holder.mealDay.text = newList.maelDay
        holder.maelName.text = newList.maelName
    }
}