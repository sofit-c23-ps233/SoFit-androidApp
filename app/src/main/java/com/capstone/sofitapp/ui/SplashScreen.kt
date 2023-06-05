package com.capstone.sofitapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.capstone.sofitapp.data.model.ViewModelFactory
import com.capstone.sofitapp.databinding.ActivitySplashScreenBinding
import com.capstone.sofitapp.ui.main.MainActivity
import com.capstone.sofitapp.ui.main.MainViewModel

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val mainViewModel by viewModels<MainViewModel> { ViewModelFactory.getInstance(this) }
    private val duration = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            mainViewModel.getSession().observe(this@SplashScreen) {
                if (it.isLogin) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    startActivity(Intent(this, WelcomeActivity::class.java))
                }
                finish()
            }
        },duration)
    }
}