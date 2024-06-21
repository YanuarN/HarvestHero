package com.harvesthero.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.harvesthero.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.harvesthero.ui.main.MainActivity
import com.harvesthero.ui.personalize.PersonalizeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        mAuth = FirebaseAuth.getInstance()

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Access the SignInButton using findViewById
        val googleSignInButton: SignInButton = findViewById(R.id.googleSignInButton)

        // Set click listener
        googleSignInButton.setOnClickListener { signIn() }
        setGooglePlusButtonText(googleSignInButton, "Masuk dengan Google")
        googleSignInButton.setSize(SignInButton.SIZE_WIDE)
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("LoginActivity", "signInWithCredential:success")
                    val user = mAuth.currentUser

                    if (isFirstTimeLogin()) {
                        // This is the user's first time logging in
                        setFirstTimeLogin(false)
                        updateUI(user, true)
                    } else {
                        // This user has logged in before
                        updateUI(user, false)
                    }
                } else {
                    Log.e("LoginActivity", "signInWithCredential:failure", task.exception)
                    Toast.makeText(this@LoginActivity, "Authentication failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    updateUI(null, false)
                }
            }
            .addOnFailureListener { e ->
                Log.e("LoginActivity", "signInWithCredential:failure", e)
                Toast.makeText(this@LoginActivity, "Authentication failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Log.d("LoginActivity", "firebaseAuthWithGoogle:" + account.id)
            firebaseAuthWithGoogle(account.idToken!!)
        } catch (e: ApiException) {
            Log.e("LoginActivity", "Google sign in failed", e)
            Toast.makeText(this, "Google Sign-In failed: ${e.statusCode}", Toast.LENGTH_SHORT).show()
            updateUI(null, false)
        }
    }

    private fun updateUI(user: FirebaseUser?, isNewUser: Boolean) {
        if (user != null) {
            if (isNewUser) {
                // Redirect to Personalization Activity for new users
                val intent = Intent(this, PersonalizeActivity::class.java)
                startActivity(intent)
            } else {
                // Redirect to Main Activity for returning users
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            finish()
        } else {
            findViewById<TextView>(R.id.googleSignInText).text = "Silahkan masuk dengan akun google"
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            if (isFirstTimeLogin()) {
                // User logged in for the first time
                Toast.makeText(this, "Selamat Datang di Harvest Heroes!", Toast.LENGTH_SHORT).show()
                updateUI(currentUser, true)
            } else {
                // User has logged in before
                updateUI(currentUser, false)
            }
        } else {
            updateUI(null, false)
        }
    }

    private fun setFirstTimeLogin(isFirstTime: Boolean) {
        val sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isFirstTime", isFirstTime)
        editor.apply()
    }

    private fun isFirstTimeLogin(): Boolean {
        val sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isFirstTime", true)
    }

    private fun setGooglePlusButtonText(signInButton: SignInButton, buttonText: String) {
        for (i in 0 until signInButton.childCount) {
            val v = signInButton.getChildAt(i)
            if (v is TextView) {
                v.text = buttonText
                return
            }
        }
    }
}
