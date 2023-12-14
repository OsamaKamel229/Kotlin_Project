package com.kotlincourse.foodapp

import android.annotation.SuppressLint
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
import com.kotlincourse.foodapp.model.BlogData
import com.kotlincourse.foodapp.model.CommentData
import com.kotlincourse.foodapp.view.CommentAdapter

class AboutMe : Fragment() {

    private lateinit var addBtn: FloatingActionButton
    private lateinit var  recv: RecyclerView
    private lateinit var commentList: ArrayList<CommentData>
    private lateinit var commentAdapter: CommentAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about_me, container, false)

        commentList = ArrayList()
        addBtn = view.findViewById(R.id.addingComment)
        recv = view.findViewById(R.id.commentRecycler)
        commentAdapter = CommentAdapter(requireContext(), commentList)

        recv.layoutManager = LinearLayoutManager(requireContext())
        recv.adapter = commentAdapter
        addBtn.setOnClickListener{addInfo()}
        return view
    }

    private fun addInfo() {

        val inflater = LayoutInflater.from(requireContext())
        val v = inflater.inflate(R.layout.add_aboutme, null)
        val comment = v.findViewById<EditText>(R.id.commentED)

        val addDialog = AlertDialog.Builder(requireContext())
        addDialog.setView(v)
        addDialog.setPositiveButton("OK"){
                dialog,_->
            val comment = comment.text.toString()

            commentList.add(
                CommentData(
                    "$comment"
                )
            )

            commentAdapter.notifyDataSetChanged()
            Toast.makeText(
                requireContext(),
                "Adding Comment Information Successfully",
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