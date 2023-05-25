package com.capstone.sofitapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.sofitapp.databinding.ActivityWelcomeBinding
import com.capstone.sofitapp.ui.auth.LoginActivity
import com.capstone.sofitapp.ui.auth.RegisterActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnRegis()
        btnLogin()
    }

    private fun btnRegis() {
        binding.btnRegisterWelcome.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun btnLogin() {
        binding.btnLoginWelcome.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}