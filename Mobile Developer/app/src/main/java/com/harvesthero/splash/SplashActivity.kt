package com.harvesthero.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.harvesthero.R
import com.harvesthero.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    private val splashTimeOut: Long = 3000 // in milliseconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Find the ImageView in the layout
        val logo: ImageView = findViewById(R.id.logo)

        // Load the animations
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)

        // Start the fade-in animation
        logo.startAnimation(fadeIn)


        // Start the main activity after the delay
        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // close the splash activity
        }, splashTimeOut)
    }
}


