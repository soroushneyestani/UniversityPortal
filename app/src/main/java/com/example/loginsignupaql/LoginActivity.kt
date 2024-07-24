package com.example.loginsignupaql

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginsignupaql.databinding.ActivitySignupBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var databasehelper: databasehelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databasehelper = databasehelper(this)

        binding.signupButton.setOnClickListener {
            val loginUsername = binding.signupUsername.text.toString()
            val loginPassword = binding.signupPassword.text.toString()
            loginDatabase(loginUsername, loginPassword)
        }

        binding.loginRedirect.setOnClickListener() {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    private fun loginDatabase(username: String, password: String) {
        val userExists = databasehelper.readUser(username, password)
        if (userExists){
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this,"Login failed",Toast.LENGTH_SHORT).show()
        }
    }
}