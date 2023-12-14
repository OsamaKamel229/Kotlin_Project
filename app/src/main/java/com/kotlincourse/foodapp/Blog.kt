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
import com.kotlincourse.foodapp.model.MaelPlannerData
import com.kotlincourse.foodapp.view.BlogAdapter

class Blog : Fragment() {

    private lateinit var addBtn: FloatingActionButton
    private lateinit var  recv: RecyclerView
    private lateinit var blogList: ArrayList<BlogData>
    private lateinit var blogAdapter: BlogAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_blog, container, false)

        blogList = ArrayList()
        addBtn = view.findViewById(R.id.addingBlog)
        recv = view.findViewById(R.id.blogRecycler)
        blogAdapter = BlogAdapter(requireContext(), blogList)

        recv.layoutManager = LinearLayoutManager(requireContext())
        recv.adapter = blogAdapter
        addBtn.setOnClickListener {addinfo()}
        return view
    }

    @SuppressLint("MissingInflatedId")
    private fun addinfo() {
        val inflater = LayoutInflater.from(requireContext())
        val v = inflater.inflate(R.layout.add_blog, null)
        val userName = v.findViewById<EditText>(R.id.userNameED)
        val postDetails = v.findViewById<EditText>(R.id.postDetailsED)

        val addDialog = AlertDialog.Builder(requireContext())
        addDialog.setView(v)
        addDialog.setPositiveButton("OK"){
                dialog,_->
            val userName = userName.text.toString()
            val postDetails = postDetails.text.toString()

            blogList.add(
                BlogData(
                    "Guest Name: $userName",
                    "Post Details: $postDetails "
                )
            )

            blogAdapter.notifyDataSetChanged()
            Toast.makeText(
                requireContext(),
                "Adding Post Information Successfully",
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

