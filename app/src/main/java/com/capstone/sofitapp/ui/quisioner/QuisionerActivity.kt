package com.capstone.sofitapp.ui.quisioner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.capstone.sofitapp.databinding.ActivityQuisionerBinding

class QuisionerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuisionerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuisionerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}