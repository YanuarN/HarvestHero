package com.harvesthero.ui.profile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.harvesthero.R
import com.google.firebase.auth.FirebaseAuth
import com.harvesthero.predict.CameraActivity
import com.harvesthero.ui.search.SearchActivity
import com.harvesthero.ui.main.MainActivity
import com.harvesthero.ui.komunitas.KomunitasActivity
import com.harvesthero.ui.komunitas.PertanyaanActivity
import com.harvesthero.ui.login.LoginActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var tvUsername: TextView
    private lateinit var tvUserStatus: TextView
    private lateinit var profileImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        tvUsername = findViewById(R.id.username)
        tvUserStatus = findViewById(R.id.user_status)
        profileImage = findViewById(R.id.profile_image)

        // Load the data from SharedPreferences
        val sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE)
        val firstName = sharedPreferences.getString("FIRST_NAME", ".")
        val lastName = sharedPreferences.getString("LAST_NAME", ".")
        val description = sharedPreferences.getString("DESCRIPTION", "Description")
        val phone = sharedPreferences.getString("PHONE", "Default Phone")
        val email = sharedPreferences.getString("EMAIL", "Default Email")
        val profileImageUri = sharedPreferences.getString("PROFILE_IMAGE_URI", "")

        // Set the data to the TextViews
        tvUsername.text = "$firstName $lastName"
        tvUserStatus.text = description

        auth = FirebaseAuth.getInstance()

        findViewById<ImageView>(R.id.btnBeranda).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnSignOut).setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        findViewById<Button>(R.id.btn_faq).setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, PertanyaanActivity::class.java))
        }

        findViewById<Button>(R.id.btn_account).setOnClickListener {
            startActivity(Intent(this, ProfileEditActivity::class.java))
        }

        findViewById<ImageView>(R.id.btnSearch).setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.btnCameraX).setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.btnKomunitas).setOnClickListener {
            val intent = Intent(this, KomunitasActivity::class.java)
            startActivity(intent)
        }

        val inviteFriendsButton: Button = findViewById(R.id.btn_invite_friends)
        inviteFriendsButton.setOnClickListener {
            shareApp()
        }
    }

    private fun shareApp() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Check out this amazing app: Harvest Heroes! Download it from: [Link to your app]")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }




}