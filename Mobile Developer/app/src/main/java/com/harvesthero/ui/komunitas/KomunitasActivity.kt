package com.harvesthero.ui.komunitas

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.harvesthero.R
import com.harvesthero.predict.CameraActivity
import com.harvesthero.ui.search.SearchActivity
import com.harvesthero.ui.main.MainActivity
import com.harvesthero.ui.profile.ProfileActivity

class KomunitasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_komunitas)

        findViewById<ImageView>(R.id.btnCameraX).setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.btnProfile).setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.btnSearch).setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.btnBeranda).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}