package com.example.easyplates

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.PopupWindow
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userIcon = findViewById<ImageView>(R.id.userImageButton)

        userIcon.setOnClickListener {
            val intent = Intent(this, UserProfile::class.java)
            startActivity(intent)
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

        val matchaCardView = findViewById<CardView>(R.id.matchaCardView)
        val garlicChickenCardView = findViewById<CardView>(R.id.garlicChickenCardView)
        val adoboCardView = findViewById<CardView>(R.id.adoboCardView)
        val macaronsCardView = findViewById<CardView>(R.id.macaronsCardView)
        val ramenCardView = findViewById<CardView>(R.id.ramenCardView)
        val coffeeCardView = findViewById<CardView>(R.id.coffeeCardView)
        val lasagnaCardView = findViewById<CardView>(R.id.lasagnaCardView)
        val beefCardView = findViewById<CardView>(R.id.beefCardView)
        val cinnamonCardView = findViewById<CardView>(R.id.cinnamonCardView)
        val pancakeCardView = findViewById<CardView>(R.id.pancakeCardView)


        matchaCardView.setOnClickListener {
            openRecipe("Iced Matcha Latte")
        }

        garlicChickenCardView.setOnClickListener {
            openRecipe("Creamy Garlic Chicken")
        }

        adoboCardView.setOnClickListener {
            openRecipe("Creamy Garlic Chicken")
        }

        macaronsCardView.setOnClickListener {
            openRecipe("Creamy Garlic Chicken")
        }

        ramenCardView.setOnClickListener {
            openRecipe("Creamy Garlic Chicken")
        }

        coffeeCardView.setOnClickListener {
            openRecipe("Creamy Garlic Chicken")
        }

        lasagnaCardView.setOnClickListener {
            openRecipe("Creamy Garlic Chicken")
        }

        beefCardView.setOnClickListener {
            openRecipe("Creamy Garlic Chicken")
        }

        cinnamonCardView.setOnClickListener {
            openRecipe("Creamy Garlic Chicken")
        }

        pancakeCardView.setOnClickListener {
            openRecipe("Creamy Garlic Chicken")
        }

        // Repeat for all other cards
    }

    private fun openRecipe(recipeName: String) {
        val intent = Intent(this, RecipeDetailActivity::class.java)
        intent.putExtra("recipeName", recipeName)
        startActivity(intent)

    }

}