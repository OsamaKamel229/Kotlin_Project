package com.kotlincourse.foodapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val userName = findViewById<EditText>(R.id.userNameLoginET)
        val password = findViewById<EditText>(R.id.passwordET)

        val sharedPreference = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val getUserName = sharedPreference.getString("USERNAME", "")
        val getPassword = sharedPreference.getString("PASSWORD","")

        if(getUserName == "admin" && getPassword == "123"){
            val i  = Intent(this, MainActivity::class.java)
            startActivity(i)
        }


        btnLogin.setOnClickListener{
            val userName = userName.text.toString()
            val password = password.text.toString()

                val editor = sharedPreference.edit()
                editor.putString("USERNAME", userName)
                editor.putString("PASSWORD", password)
                editor.apply()

                val i  = Intent(this, MainActivity::class.java)
                startActivity(i)

        }
    }
}