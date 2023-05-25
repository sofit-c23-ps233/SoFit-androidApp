package com.capstone.sofitapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.sofitapp.databinding.ActivityRegisterBinding
import com.capstone.sofitapp.ui.WelcomeActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnLogin()
        btnBack()
    }


    private fun btnBack() {
        binding.ivBack.setOnClickListener {
            val intent = Intent(this@RegisterActivity, WelcomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun btnLogin() {
        binding.tvLogin2.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}