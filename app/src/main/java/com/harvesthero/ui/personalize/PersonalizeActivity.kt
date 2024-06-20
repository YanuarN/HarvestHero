package com.harvesthero.ui.personalize

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.harvesthero.R
import com.harvesthero.ui.main.MainActivity

class PersonalizeActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButtonRice: RadioButton
    private lateinit var radioButtonCorn: RadioButton
    private lateinit var radioButtonBanana: RadioButton
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personalize)

        radioGroup = findViewById(R.id.radioGroup)
        radioButtonRice = findViewById(R.id.radioButtonRice)
        radioButtonCorn = findViewById(R.id.radioButtonCorn)
        radioButtonBanana = findViewById(R.id.radioButtonBanana)
        nextButton = findViewById(R.id.nextButton)

        nextButton.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            var selectedCrop = ""

            when (selectedId) {
                radioButtonRice.id -> selectedCrop = getString(R.string.pilih_padi)
                radioButtonCorn.id -> selectedCrop = getString(R.string.pilih_jagung)
                radioButtonBanana.id -> selectedCrop = getString(R.string.pilih_pisang)
            }

            // Simpan data ke SharedPreferences
            val sharedPref = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("selectedCrop", selectedCrop)
                apply()
            }

            // Kirim data ke MainActivity
            val intent = Intent(this@PersonalizeActivity, MainActivity::class.java)
            intent.putExtra("selectedCrop", selectedCrop)
            startActivity(intent)
        }
    }
}


