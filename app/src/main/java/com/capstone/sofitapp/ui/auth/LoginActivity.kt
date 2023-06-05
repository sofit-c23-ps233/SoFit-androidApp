package com.capstone.sofitapp.ui.auth

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.capstone.sofitapp.R
import com.capstone.sofitapp.data.model.User
import com.capstone.sofitapp.data.model.ViewModelFactory
import com.capstone.sofitapp.databinding.ActivityLoginBinding
import com.capstone.sofitapp.ui.main.MainActivity
import com.capstone.sofitapp.ui.WelcomeActivity


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var factory: ViewModelFactory
    private val loginViewModel: LoginViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()
        setupAction()
        setupViewModel()
        btnBack()
        btnRegis()
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
        val password = ObjectAnimator.ofFloat(binding.tlPassword, View.ALPHA, 1f).setDuration(500)
        val login = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(500)
        val register1 = ObjectAnimator.ofFloat(binding.tvRegis, View.ALPHA, 1f).setDuration(500)
        val register2 = ObjectAnimator.ofFloat(binding.tvRegis2, View.ALPHA, 1f).setDuration(500)

        val together = AnimatorSet().apply {
            playTogether(email, password, login, register1, register2)
        }

        AnimatorSet().apply {
            playSequentially(back, title1, title2, together)
            startDelay = 400
        }.start()
    }

    private fun setupAction() {
        binding.apply {
            if (editEmail.length() == 0 && editPassword.length() == 0) {
                editEmail.error = getString(R.string.required_field_email)
                editPassword.setError(getString(R.string.required_field_password), null)
                loginButton.isEnabled = false
            } else if (editEmail.length() != 0 && editPassword.length() != 0) {
                loginButton.isEnabled = true
            }

            editEmail.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    loginButton.isEnabled = editEmail.text!!.isNotEmpty() && editPassword.text!!.isNotEmpty()
                }
                override fun afterTextChanged(s: Editable?) {}
            })

            editPassword.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    loginButton.isEnabled = editPassword.length() >= 8
                }
                override fun afterTextChanged(s: Editable?) {}
            })

            loginButton.setOnClickListener {
                showLoading()
                postText()
                showToast()
                loginViewModel.login()
                moveActivity()
            }
        }
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun showToast() {
        loginViewModel.toastText.observe(this@LoginActivity) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(
                    this@LoginActivity, toastText, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun showLoading() {
        loginViewModel.isLoading.observe(this@LoginActivity) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun postText() {
        binding.apply {
            loginViewModel.doLogin(
                editEmail.text.toString(),
                editPassword.text.toString()
            )
        }

        loginViewModel.loginResponse.observe(this@LoginActivity) { response ->
            saveSession(
                User(
                    response.data?.name.toString(),
                    response.data?.email.toString(),
                    true
                )
            )
        }
    }

    private fun moveActivity() {
        loginViewModel.loginResponse.observe(this@LoginActivity) { response ->
            if (!response.error) {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun saveSession(session: User){
        loginViewModel.saveSession(session)
    }

    private fun btnBack() {
        binding.ivBack.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }

    private fun btnRegis() {
        binding.tvRegis2.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}