package com.kotlincourse.foodapp

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar


import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kotlincourse.foodapp.databinding.ActivityMainBinding
import com.kotlincourse.foodapp.model.RecipeData
import com.kotlincourse.foodapp.view.RecipeAdapter

class Recipes : Fragment() {

        private lateinit var addsBtn: FloatingActionButton
        private lateinit var recv: RecyclerView
        private lateinit var recipesList: ArrayList<RecipeData>
        private lateinit var recipeAdapter: RecipeAdapter
        private lateinit var imageView: ImageView
        private val pickImage = 100
        private var imageUri: Uri? = null
        private lateinit var binding: ActivityMainBinding

        val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            val galleryUri = it
            try {
                imageView.setImageURI(galleryUri)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recipes, container, false)

        recipesList = ArrayList()
        addsBtn = view.findViewById(R.id.addingRecipes)
        recv = view.findViewById(R.id.mRecycler)
        recipeAdapter = RecipeAdapter(requireContext(),recipesList)

        recv.layoutManager = LinearLayoutManager(requireContext())
        recv.adapter = recipeAdapter
        addsBtn.setOnClickListener{ addInfo()}

        return view
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }
    }

    private fun addInfo() {
        val inflater = LayoutInflater.from(requireContext())
        val v = inflater.inflate(R.layout.add_item, null)

        val recipeName = v.findViewById<EditText>(R.id.recipeNameET)
        val recipeIngredients = v.findViewById<EditText>(R.id.recipeIngredientsET)
        val recipeInstruction = v.findViewById<EditText>(R.id.recipeInstructionET)
        val recipeRate = v.findViewById<RatingBar>(R.id.recipeUserRateET)

        imageView = v.findViewById(R.id.imageView)
        val button: Button = v.findViewById(R.id.buttonLoadPicture)


        button.setOnClickListener {
//            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            startActivityForResult(gallery, pickImage)
//            selectImage()
            galleryLauncher.launch("image/*")
        }

        val addDialog = AlertDialog.Builder(requireContext())
        addDialog.setView(v)
        addDialog.setPositiveButton("OK"){
            dialog,_->
            val recipeName = recipeName.text.toString()
            val recipeIngredients = recipeIngredients.text.toString()
            val recipeInstruction = recipeInstruction.text.toString()
            val recipeRate = recipeRate.rating

            recipesList.add(
                RecipeData(
                    "Name: $recipeName",
                    "Ingredients: $recipeIngredients",
                    "Instruction: $recipeInstruction",
                    imageView,
                    recipeRate
                )
            )

            recipeAdapter.notifyDataSetChanged()
            Toast.makeText(
                requireContext(),
                "Adding Recipe Information Successfully",
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
//    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
//        // Callback is invoked after the user selects a media item or closes the
//        // photo picker.
//        if (uri != null) {
//            Log.d("PhotoPicker", "Selected URI: $uri")
//        } else {
//            Log.d("PhotoPicker", "No media selected")
//        }
//    }
//    fun selectImage(){
//        // Launch the photo picker and let the user choose only images.
//                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
//                val mimeType = "image/jpg"
//                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.SingleMimeType(mimeType)))
//    }
}