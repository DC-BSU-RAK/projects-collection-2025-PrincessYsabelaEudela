package com.example.easyplates

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UserProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val homeIcon = findViewById<ImageView>(R.id.homeImageButton)

        homeIcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val infoImageButton: ImageButton = findViewById(R.id.infoImageButton)
        infoImageButton.setOnClickListener{
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.activity_instructions_popup, null)

            val width = 1000
            val height = 1400

            val i = PopupWindow(popupView, width, height, true)
            i.showAtLocation(popupView, Gravity.CENTER, 0, 0)

            val closeButton = popupView.findViewById<Button>(R.id.closeButton)
            closeButton.setOnClickListener{
                i.dismiss()
            }
        }

        val sharedPref: SharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val username = sharedPref.getString("username", "N/A")
        val email = sharedPref.getString("email", "N/A")

        val welcomeTextView = findViewById<TextView>(R.id.welcomeTextView)
        welcomeTextView.text = "Welcome, $username!"

        val userInfoTextView = findViewById<TextView>(R.id.userInfoTextView)
        userInfoTextView.text = "Email: $email"

    }
}