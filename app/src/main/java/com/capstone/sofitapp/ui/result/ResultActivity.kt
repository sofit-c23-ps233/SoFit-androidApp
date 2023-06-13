package com.capstone.sofitapp.ui.result


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import com.capstone.sofitapp.R
import com.capstone.sofitapp.data.model.ViewModelFactory
import com.capstone.sofitapp.databinding.ActivityResultBinding
import com.capstone.sofitapp.ui.main.MainActivity


class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var factory: ViewModelFactory
    private val resultViewModel: ResultViewModel by viewModels { factory }
    private var gender = ""
    private var height = ""
    private var weight = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        // get data from intent and set to textview
        val intent = intent
        gender = intent.getStringExtra("gender") ?: ""
        gender = if (gender == "Laki-laki") "1" else "0"
        height = intent.getStringExtra("height") ?: ""
        weight = intent.getStringExtra("weight") ?: ""

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultViewModel.doPredict(gender, height, weight)
        resultViewModel.predictResponse.observe(this) {
            if (it?.prediction != null) {
                resultViewModel.doRecommendation(it.prediction)
                binding.apply {
                    tvHasilAngka.text = it.category
                    when (it.category) {
                        "Extremely Weak" -> {
                            tvHasilKeterangan.text = getString(R.string.extremely_weak)
                            //set ivArrow constraint
                            ivArrow.updateLayoutParams<ConstraintLayout.LayoutParams> {
                                this.bottomToBottom = ivMerah.id
                                this.topToTop = ivMerah.id
                                this.startToStart = ivMerah.id
                                this.endToEnd = ivMerah.id
                            }
                        }

                        "Weak" -> {
                            tvHasilKeterangan.text = getString(R.string.weak)
                            ivArrow.updateLayoutParams<ConstraintLayout.LayoutParams> {
                                this.bottomToBottom = ivKuning.id
                                this.topToTop = ivKuning.id
                                this.startToStart = ivKuning.id
                                this.endToEnd = ivKuning.id
                            }
                        }

                        "Ideal" -> {
                            tvHasilKeterangan.text = getString(R.string.ideal)
                            ivArrow.updateLayoutParams<ConstraintLayout.LayoutParams> {
                                this.bottomToBottom = ivHijau.id
                                this.topToTop = ivHijau.id
                                this.startToStart = ivHijau.id
                                this.endToEnd = ivHijau.id
                            }
                        }

                        "Overweight" -> {
                            tvHasilKeterangan.text = getString(R.string.overweight)
                            ivArrow.updateLayoutParams<ConstraintLayout.LayoutParams> {
                                this.bottomToBottom = ivKuning2.id
                                this.topToTop = ivKuning2.id
                                this.startToStart = ivKuning2.id
                                this.endToEnd = ivKuning2.id
                            }
                        }

                        "Obesity" -> {
                            tvHasilKeterangan.text = getString(R.string.obesity)
                            ivArrow.updateLayoutParams<ConstraintLayout.LayoutParams> {
                                this.bottomToBottom = ivOrange.id
                                this.topToTop = ivOrange.id
                                this.startToStart = ivOrange.id
                                this.endToEnd = ivOrange.id
                            }
                        }

                        "Extreme Obesity" -> {
                            tvHasilKeterangan.text = getString(R.string.extreme_obesity)
                            ivArrow.updateLayoutParams<ConstraintLayout.LayoutParams> {
                                this.bottomToBottom = ivMerah2.id
                                this.topToTop = ivMerah2.id
                                this.startToStart = ivMerah2.id
                                this.endToEnd = ivMerah2.id
                            }
                        }
                    }
                }
            }
        }

        resultViewModel.recommendationResponse.observe(this) {
            if (it?.data != null) {
                val categoryId = it.data[0]
                binding.apply {
                    tvDeskripsiMakanan.text = categoryId?.food
                    tvDeskripsiOlahraga.text = categoryId?.exercise
                }
            }
        }

        btnBack()
        btnSave()
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun btnBack() {
        binding.ivBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }

    private fun btnSave() {
        binding.btnSimpan.setOnClickListener {
            // Mendapatkan nilai gender, height, dan weight dari intent
            val gender = intent.getStringExtra("gender")
            val height = intent.getStringExtra("height")
            val weight = intent.getStringExtra("weight")

            // Mendapatkan data history yang ada dari SharedPreferences
            val sharedPreferences = getSharedPreferences("HistoryPrefs", Context.MODE_PRIVATE)
            val historySet = sharedPreferences.getStringSet("historyList", setOf())

            // Membuat HashSet baru dengan data history yang sudah ada ditambah data baru
            val updatedHistorySet = HashSet<String>()
            updatedHistorySet.addAll(historySet ?: emptySet())
            val historyData = "$gender, $height, $weight"
            updatedHistorySet.add(historyData)

            // Menyimpan data history yang diperbarui ke SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putStringSet("historyList", updatedHistorySet)
            editor.apply()

            // Kembali ke MainActivity
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}