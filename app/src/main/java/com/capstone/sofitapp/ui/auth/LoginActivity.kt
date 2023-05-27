package com.capstone.sofitapp.ui.auth

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.capstone.sofitapp.databinding.ActivityLoginBinding
import com.capstone.sofitapp.ui.MainActivity
import com.capstone.sofitapp.ui.WelcomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()
        btnBack()
        btnRegis()
        btnLogin()
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivLogin, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val back = ObjectAnimator.ofFloat(binding.ivBack, View.ALPHA, 1f).setDuration(400)
        val title1 = ObjectAnimator.ofFloat(binding.tvTitleLogin, View.ALPHA, 1f).setDuration(400)
        val title2 = ObjectAnimator.ofFloat(binding.tvTitleLogin2, View.ALPHA, 1f).setDuration(400)
        val email = ObjectAnimator.ofFloat(binding.editEmail, View.ALPHA, 1f).setDuration(500)
        val password = ObjectAnimator.ofFloat(binding.editPassword, View.ALPHA, 1f).setDuration(500)
        val login = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(500)
        val register1 = ObjectAnimator.ofFloat(binding.tvRegis, View.ALPHA, 1f).setDuration(500)
        val register2 = ObjectAnimator.ofFloat(binding.tvRegis2, View.ALPHA, 1f).setDuration(500)

        val together = AnimatorSet().apply {
            playTogether(email, password, login, register1, register2)
        }

        AnimatorSet().apply {
            playSequentially(back, title1, title2,  together)
            startDelay = 400
        }.start()
    }

    private fun btnBack() {
        binding.ivBack.setOnClickListener {
            val intent = Intent(this@LoginActivity, WelcomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }

    private fun btnRegis() {
        binding.tvRegis2.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun btnLogin() {
        binding.loginButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}