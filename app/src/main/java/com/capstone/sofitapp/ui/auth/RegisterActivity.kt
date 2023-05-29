package com.capstone.sofitapp.ui.auth

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.capstone.sofitapp.databinding.ActivityRegisterBinding
import com.capstone.sofitapp.ui.WelcomeActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()
        btnLogin()
        btnBack()
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivRegister, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val back = ObjectAnimator.ofFloat(binding.ivBack, View.ALPHA, 1f).setDuration(400)
        val title1 = ObjectAnimator.ofFloat(binding.tvTitleRegister, View.ALPHA, 1f).setDuration(400)
        val title2 = ObjectAnimator.ofFloat(binding.tvTitleRegister2, View.ALPHA, 1f).setDuration(400)
        val name = ObjectAnimator.ofFloat(binding.tvName, View.ALPHA, 1f).setDuration(500)
        val editName = ObjectAnimator.ofFloat(binding.editName, View.ALPHA, 1f).setDuration(500)
        val email = ObjectAnimator.ofFloat(binding.tvEmail, View.ALPHA, 1f).setDuration(500)
        val editEmail = ObjectAnimator.ofFloat(binding.editEmail, View.ALPHA, 1f).setDuration(500)
        val password = ObjectAnimator.ofFloat(binding.tvPassword, View.ALPHA, 1f).setDuration(500)
        val editPassword = ObjectAnimator.ofFloat(binding.tlPassword, View.ALPHA, 1f).setDuration(500)
        val confirmPassword = ObjectAnimator.ofFloat(binding.tvKonfirmasiPassword, View.ALPHA, 1f).setDuration(500)
        val editConfirmPass = ObjectAnimator.ofFloat(binding.tlKonfirmasiPassword, View.ALPHA, 1f).setDuration(500)
        val register = ObjectAnimator.ofFloat(binding.regisButton, View.ALPHA, 1f).setDuration(500)
        val login1 = ObjectAnimator.ofFloat(binding.tvLogin, View.ALPHA, 1f).setDuration(500)
        val login2 = ObjectAnimator.ofFloat(binding.tvLogin2, View.ALPHA, 1f).setDuration(500)

        val together = AnimatorSet().apply {
            playTogether(name, editName, email, editEmail, password, editPassword, confirmPassword, editConfirmPass, register, login1, login2)
        }

        AnimatorSet().apply {
            playSequentially(back, title1, title2,  together)
            startDelay = 400
        }.start()
    }

    private fun btnBack() {
        binding.ivBack.setOnClickListener {
            val intent = Intent(this@RegisterActivity, WelcomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }

    private fun btnLogin() {
        binding.tvLogin2.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}