package com.capstone.sofitapp.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.capstone.sofitapp.databinding.ActivityWelcomeBinding
import com.capstone.sofitapp.ui.auth.LoginActivity
import com.capstone.sofitapp.ui.auth.RegisterActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()
        setupAction()
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivWelcome, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        ObjectAnimator.ofFloat(binding.tvAppName, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
        
        val appName = ObjectAnimator.ofFloat(binding.tvAppName, View.ALPHA, 1f).setDuration(500)
        val title = ObjectAnimator.ofFloat(binding.tvTitleWelcome, View.ALPHA, 1f).setDuration(500)
        val register = ObjectAnimator.ofFloat(binding.btnRegisterWelcome, View.ALPHA, 1f).setDuration(500)
        val divider1 = ObjectAnimator.ofFloat(binding.divider1, View.ALPHA, 1f).setDuration(500)
        val desc = ObjectAnimator.ofFloat(binding.tvdescWelcome, View.ALPHA, 1f).setDuration(500)
        val divider2 = ObjectAnimator.ofFloat(binding.divider2, View.ALPHA, 1f).setDuration(500)
        val login = ObjectAnimator.ofFloat(binding.btnLoginWelcome, View.ALPHA, 1f).setDuration(500)

        val together = AnimatorSet().apply {
            playTogether(register, divider1, desc, divider2, login)
        }

        AnimatorSet().apply {
            playSequentially(appName, title, together)
            start()
        }
    }

    private fun setupAction() {
        binding.apply {
            btnRegisterWelcome.setOnClickListener {
                startActivity(Intent(this@WelcomeActivity, RegisterActivity::class.java))
            }
            btnLoginWelcome.setOnClickListener {
                startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java))
            }
        }
    }
}