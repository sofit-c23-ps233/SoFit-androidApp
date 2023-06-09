package com.capstone.sofitapp.ui.quisioner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.*
import com.capstone.sofitapp.R
import com.capstone.sofitapp.databinding.ActivityQuisionerBinding
import com.capstone.sofitapp.ui.main.MainActivity
import com.capstone.sofitapp.ui.result.ResultActivity

class QuisionerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuisionerBinding
    private val viewModel : QuisionerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuisionerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<Quisioner1Fragment>(R.id.fragmentContainer)
            }
        }

        binding.btnNext.btnKembali.setOnClickListener {
            val prevFragment = when (supportFragmentManager.findFragmentById(R.id.fragmentContainer)) {
                is Quisioner2Fragment -> Quisioner1Fragment()
                is Quisioner3Fragment -> Quisioner2Fragment()
                else -> null // Fragment pertama, tidak ada Fragment sebelumnya
            }

            prevFragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, it)
                    .commit()
                binding.btnNext.btnLanjut.visibility = View.VISIBLE
                binding.btnNext.btnHasil.visibility = View.GONE
                binding.btnNext.btnKembali.isEnabled = true
            }
        }

        binding.btnNext.btnLanjut.setOnClickListener {
            Log.d("Quisioner", viewModel.gender)
            Log.d("Quisioner", viewModel.height)
            Log.d("Quisioner", viewModel.weight)
            val nextFragment =
                when (supportFragmentManager.findFragmentById(R.id.fragmentContainer)) {
                    is Quisioner1Fragment -> Quisioner2Fragment()
                    is Quisioner2Fragment -> Quisioner3Fragment()
                    else -> null // Fragment terakhir, tidak ada Fragment selanjutnya
                }

            nextFragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, it)
                    .commit()
                binding.btnNext.btnLanjut.visibility = View.VISIBLE
                binding.btnNext.btnHasil.visibility = View.GONE
                binding.btnNext.btnKembali.isEnabled = true

                if (it is Quisioner3Fragment) {
                    binding.btnNext.btnLanjut.visibility = View.GONE
                    binding.btnNext.btnHasil.visibility = View.VISIBLE
                }
            }
        }

        binding.btnNext.btnHasil.setOnClickListener {
            Log.d("Quisioner", viewModel.gender)
            Log.d("Quisioner", viewModel.height)
            Log.d("Quisioner", viewModel.weight)
            val intent = Intent(this, ResultActivity::class.java)
            //send gender, height, weight to ResultActivity
            intent.putExtra("gender", viewModel.gender)
            intent.putExtra("height", viewModel.height)
            intent.putExtra("weight", viewModel.weight)
            startActivity(intent)
            finish()
        }

        binding.ivBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }

    @Suppress("DEPRECATION")
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}