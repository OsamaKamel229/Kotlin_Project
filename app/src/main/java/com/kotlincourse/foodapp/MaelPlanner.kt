package com.kotlincourse.foodapp

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kotlincourse.foodapp.model.MaelPlannerData
import com.kotlincourse.foodapp.view.MaelPlannerAdapter


class MaelPlanner : Fragment() {
    private lateinit var addBtn: FloatingActionButton
    private lateinit var  recv: RecyclerView
    private lateinit var maelPlannerList: ArrayList<MaelPlannerData>
    private lateinit var maelPlannerAdapter: MaelPlannerAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_mael_planner, container, false)

        maelPlannerList = ArrayList()
        addBtn = view.findViewById(R.id.addingMaelPlanner)
        recv = view.findViewById(R.id.maelPlannerRecycler)
        maelPlannerAdapter = MaelPlannerAdapter(requireContext(), maelPlannerList)

        recv.layoutManager = LinearLayoutManager(requireContext())
        recv.adapter = maelPlannerAdapter
        addBtn.setOnClickListener{addInfo()}

        return view
    }

    private fun addInfo() {
        val inflater = LayoutInflater.from(requireContext())
        val v = inflater.inflate(R.layout.add_mael_planner, null)

        val maelName = v.findViewById<EditText>(R.id.maelNameET)
        val maelDay = v.findViewById<EditText>(R.id.dayMaelPlanerET)

        val addDialog = AlertDialog.Builder(requireContext())
        addDialog.setView(v)
        addDialog.setPositiveButton("OK"){
                dialog,_->
            val mealName = maelName.text.toString()
            val maelDay = maelDay.text.toString()

            maelPlannerList.add(
                MaelPlannerData(
                    "Mael Day: $maelDay",
                    "Mael Name: $mealName "
                )
            )

            maelPlannerAdapter.notifyDataSetChanged()
            Toast.makeText(
                requireContext(),
                "Adding Mael Pane Information Successfully",
                Toast.LENGTH_SHORT
            ).show()
            dialog.dismiss()
        }

        addDialog.setNegativeButton("Cancel"){
            dialog,_->
            dialog.dismiss()
            Toast.makeText(
                requireContext(),
                "Cancel",
                Toast.LENGTH_SHORT
            ).show()
        }
        addDialog.create()
        addDialog.show()
    }
}