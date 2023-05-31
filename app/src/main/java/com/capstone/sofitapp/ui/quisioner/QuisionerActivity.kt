package com.capstone.sofitapp.ui.quisioner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.capstone.sofitapp.ui.quisioner.Quisioner1Fragment
import androidx.fragment.app.*
import com.capstone.sofitapp.R
import com.capstone.sofitapp.databinding.ActivityQuisionerBinding
import com.capstone.sofitapp.ui.result.ResultActivity

class QuisionerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuisionerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuisionerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null) {
            supportFragmentManager.commitNow {
                setReorderingAllowed(true)
                add<Quisioner1Fragment>(R.id.fragmentContainer)
            }
        }

        binding.ivBack.setOnClickListener {
            val prevFragment = Quisioner1Fragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, prevFragment)
                .commit()
        }

        binding.btnNext.btnKembali.setOnClickListener {
            val prevFragment = Quisioner1Fragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, prevFragment)
                .commit()
        }

        binding.btnNext.btnLanjut.setOnClickListener {
            val nextFragment = Quisioner2Fragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, nextFragment)
                .commit()
        }

        binding.btnNext.btnLanjut.setOnClickListener {
            val prevFragment = Quisioner3Fragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, prevFragment)
                .commit()
        }

        binding.btnNext.btnLanjut.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}