package com.capstone.sofitapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.sofitapp.databinding.ActivityLoginBinding
import com.capstone.sofitapp.ui.WelcomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnBack()
        btnRegis()
    }

    private fun btnBack() {
        binding.ivBack.setOnClickListener {
            val intent = Intent(this@LoginActivity, WelcomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun btnRegis() {
        binding.tvRegis2.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}