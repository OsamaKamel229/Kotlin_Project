package com.kotlincourse.foodapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton



class Contact : Fragment() {

    private val CALL_PERMISSION_REQUEST_CODE = 123

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_contact, container, false)

        // Find the FloatingActionButtons
        val phoneFab: FloatingActionButton = view.findViewById(R.id.phoneDetails)
        val emailFab: FloatingActionButton = view.findViewById(R.id.emailDetails)

        // Set OnClickListener for the phone FloatingActionButton
        phoneFab.setOnClickListener {
            // Check for CALL_PHONE permission
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                // Permission is granted, make a phone call
                makePhoneCall()
            } else {
                // Request the CALL_PHONE permission
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.CALL_PHONE),
                    CALL_PERMISSION_REQUEST_CODE
                )
            }
        }

        // Set OnClickListener for the email FloatingActionButton
        emailFab.setOnClickListener {
            // Start an email intent
            sendEmail()
        }

        return view
    }

    // Function to make a phone call
    private fun makePhoneCall() {
        val phoneNumber = "tel:" + "3199882349"
        val dialIntent = Intent(Intent.ACTION_CALL, Uri.parse(phoneNumber))
        startActivity(dialIntent)
    }

    // Function to send an email
    private fun sendEmail() {
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:") // only email apps should handle this
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("okamel@miu.edu"))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
        if (emailIntent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(emailIntent)
        } else {
            // Handle the case where no email app is available
            Toast.makeText(
                requireContext(),
                "No email app found",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, make the phone call
                makePhoneCall()
            } else {
                // Permission denied, show a message
                Toast.makeText(
                    requireContext(),
                    "Phone call permission denied",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}