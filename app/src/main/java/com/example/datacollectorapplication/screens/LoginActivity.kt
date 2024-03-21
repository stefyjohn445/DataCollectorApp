package com.example.datacollectorapplication.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.datacollectorapplication.FacilityActivity
import com.example.datacollectorapplication.R
import com.example.datacollectorapplication.screens.users.UsersActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val signInButton = findViewById<Button>(R.id.signInButton)
        signInButton.setOnClickListener {
            val intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
        }
    }
}