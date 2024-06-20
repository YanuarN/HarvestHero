package com.harvesthero.predict

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.harvesthero.R

class TutorialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        findViewById<Button>(R.id.btnMengerti).setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
    }
}