package com.harvesthero.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.harvesthero.R
import com.harvesthero.predict.CameraActivity
import com.harvesthero.ui.search.SearchActivity
import com.harvesthero.ui.komunitas.KomunitasActivity
import com.harvesthero.ui.profile.ProfileActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ImageView>(R.id.btnCameraX).setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.btnProfile).setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnTakePicture).setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

        findViewById<ImageView>(R.id.btnSearch).setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
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

    }
}


