package com.harvesthero.predict

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.harvesthero.R
import com.harvesthero.ui.komunitas.KomunitasActivity
import com.harvesthero.ui.main.MainActivity
import com.harvesthero.ui.profile.ProfileActivity
import com.harvesthero.ui.search.SearchActivity

class RecommendActivity : AppCompatActivity() {

    private lateinit var plantImage: ImageView
    private lateinit var plantTitle: TextView
    private lateinit var recommendAction: TextView
//    private lateinit var responseCodeView: TextView
//    private lateinit var responseMessageView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recommend)

        // Initialize views
        plantImage = findViewById(R.id.plant_image)
        plantTitle = findViewById(R.id.plant_title)
        recommendAction = findViewById(R.id.recommend_action)
//        responseCodeView = findViewById(R.id.response_code)
//        responseMessageView = findViewById(R.id.response_message)

        // Get intent extras
        val disease = intent.getStringExtra("disease") ?: "Unknown Disease"
        val recommendation = intent.getStringExtra("recommendation") ?: "No recommendation available"
        val imageUriString = intent.getStringExtra("image_uri")
        val responseCode = intent.getStringExtra("response_code") ?: "No Code"
        val responseMessage = intent.getStringExtra("response_message") ?: "No Message"

        // Set text values
        plantTitle.text = disease
        recommendAction.text = recommendation
//        responseCodeView.text = "Response Code: $responseCode"
//        responseMessageView.text = "Response Message: $responseMessage"

        // Set image
        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString)
            plantImage.setImageURI(imageUri)
        }

        findViewById<ImageView>(R.id.btnBeranda).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.btnSearch).setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.btnKomunitas).setOnClickListener {
            val intent = Intent(this, KomunitasActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.btnProfile).setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.btnCameraX).setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }
    }
}
