package com.example.colormix

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    data class ColorOption(val name: String, val resId: Int)
    data class MixResult(val resultImage: Int, val relatedImages: List<Int>)

    val selectedColors = mutableListOf<ColorOption>()

    // Define your color mix results with group images
    val mixResults = mapOf(
        setOf("Red", "Blue") to MixResult(
            resultImage = R.drawable.purple,
            relatedImages = listOf(
                R.drawable.purple1,
                R.drawable.purple2,
                R.drawable.purple3,
                R.drawable.purple4
            )
        ),
        setOf("Red", "Green") to MixResult(
            resultImage = R.drawable.brown,
            relatedImages = listOf(
                R.drawable.brown1,
                R.drawable.brown2,
                R.drawable.brown3,
                R.drawable.brown4
            )
        ),
        setOf("Red", "Yellow") to MixResult(
            resultImage = R.drawable.orange,
            relatedImages = listOf(
                R.drawable.orange1,
                R.drawable.orange2,
                R.drawable.orange3,
                R.drawable.orange4
            )
        ),
        setOf("Blue", "Green") to MixResult(
            resultImage = R.drawable.cyan,
            relatedImages = listOf(
                R.drawable.cyan1,
                R.drawable.cyan2,
                R.drawable.cyan3,
                R.drawable.cyan4
            )
        ),
        setOf("Blue", "Yellow") to MixResult(
            resultImage = R.drawable.green,
            relatedImages = listOf(
                R.drawable.green1,
                R.drawable.green2,
                R.drawable.green3,
                R.drawable.green4
            )
        ),
        setOf("Green", "Yellow") to MixResult(
            resultImage = R.drawable.lime,
            relatedImages = listOf(
                R.drawable.lime1,
                R.drawable.lime2,
                R.drawable.lime3,
                R.drawable.lime4
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get references to UI elements
        val mixBtn = findViewById<Button>(R.id.mixButton)
        val resetBtn = findViewById<Button>(R.id.resetButton)
        val resultImage = findViewById<ImageView>(R.id.resultImage)
        val related1 = findViewById<ImageView>(R.id.related1)
        val related2 = findViewById<ImageView>(R.id.related2)
        val related3 = findViewById<ImageView>(R.id.related3)
        val related4 = findViewById<ImageView>(R.id.related4)
        val mixGroup = findViewById<LinearLayout>(R.id.resultsGroup)

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

        // Setup color buttons with color data
        setupColorButton(findViewById(R.id.redImageButton), R.drawable.red, "Red")
        setupColorButton(findViewById(R.id.blueImageButton), R.drawable.blue, "Blue")
        setupColorButton(findViewById(R.id.greenImageButton), R.drawable.green, "Green")
        setupColorButton(findViewById(R.id.yellowImageButton), R.drawable.yellow, "Yellow")


        mixBtn.setOnClickListener {
            if (selectedColors.size == 2) {
                val names = setOf(selectedColors[0].name, selectedColors[1].name)
                val result = mixResults[names]

                if (result != null) {
                    Toast.makeText(this, "Mixing ${names.joinToString(" + ")} = result", Toast.LENGTH_SHORT).show()

                    resultImage.setImageResource(result.resultImage)
                    related1.setImageResource(result.relatedImages[0])
                    related2.setImageResource(result.relatedImages[1])
                    related3.setImageResource(result.relatedImages[2])
                    related4.setImageResource(result.relatedImages[3])

                    resultImage.visibility = ImageView.VISIBLE
                    mixGroup.visibility = View.VISIBLE
                } else {
                    Toast.makeText(this, "Mix not found for ${names.joinToString(" + ")}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Reset Button: Clears selections and hides result image
        resetBtn.setOnClickListener {
            selectedColors.clear()
            resultImage.setImageDrawable(null)
            related1.setImageDrawable(null)
            related2.setImageDrawable(null)
            related3.setImageDrawable(null)
            related4.setImageDrawable(null)
            resultImage.visibility = ImageView.GONE
            mixGroup.visibility = View.GONE
        }
    }

    // Function to attach color selection behavior to a button
    fun setupColorButton(button: ImageButton, resId: Int, colorName: String) {
        button.setOnClickListener {
            if (selectedColors.size < 2) {
                selectedColors.add(ColorOption(colorName, resId)) // Add to list
                Toast.makeText(this, "$colorName selected", Toast.LENGTH_SHORT).show()
            }
        }
    }
}