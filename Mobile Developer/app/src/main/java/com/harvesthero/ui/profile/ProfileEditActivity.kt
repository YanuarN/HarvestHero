package com.harvesthero.ui.profile

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.harvesthero.R
import com.harvesthero.ui.main.MainActivity

class ProfileEditActivity : AppCompatActivity() {

    private lateinit var profileImage: ImageView
    private val PICK_IMAGE_REQUEST = 1
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etDescription: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit)

        profileImage = findViewById(R.id.profile_image)
        val btnChangePhoto: ImageButton = findViewById(R.id.btn_change_photo)
        sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE)

        btnChangePhoto.setOnClickListener {
            openGallery()
        }

        findViewById<ImageView>(R.id.btnBeranda).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        etFirstName = findViewById(R.id.et_first_name)
        etLastName = findViewById(R.id.et_last_name)
        etDescription = findViewById(R.id.et_description)
        etPhone = findViewById(R.id.et_phone)
        etEmail = findViewById(R.id.et_email)
        btnSave = findViewById(R.id.btn_save)

        btnSave.setOnClickListener {
            val firstName = etFirstName.text.toString()
            val lastName = etLastName.text.toString()
            val description = etDescription.text.toString()
            val phone = etPhone.text.toString()
            val email = etEmail.text.toString()

            // Simpan data ke SharedPreferences
            val sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("FIRST_NAME", firstName)
            editor.putString("LAST_NAME", lastName)
            editor.putString("DESCRIPTION", description)
            editor.putString("PHONE", phone)
            editor.putString("EMAIL", email)
            editor.apply()
            // Kembali ke ProfileActivity
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val imageUri = data.data
            profileImage.setImageURI(imageUri)

            // Save image URI to SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("PROFILE_IMAGE_URI", imageUri.toString())
            editor.apply()
        }
    }

}