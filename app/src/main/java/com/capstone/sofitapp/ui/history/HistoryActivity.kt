package com.capstone.sofitapp.ui.history

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.sofitapp.data.model.ViewModelFactory
import com.capstone.sofitapp.databinding.ActivityHistoryBinding
import com.capstone.sofitapp.ui.main.MainActivity

class HistoryActivity : AppCompatActivity(), HistoryAdapter.OnHistoryItemClickListener {

    private lateinit var binding : ActivityHistoryBinding
    private lateinit var factory: ViewModelFactory
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var historyDataList: MutableList<String>
//    private val historyViewModel: HistoryViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()

        historyDataList = loadHistoryData().toMutableList()
        setupRecyclerView()

        btnBack()
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun setupRecyclerView() {
        historyAdapter = HistoryAdapter(historyDataList, this)
        binding.rvListHistory.adapter = historyAdapter
        binding.rvListHistory.layoutManager = LinearLayoutManager(this)
    }

    private fun loadHistoryData(): List<String> {
        val sharedPreferences = getSharedPreferences("HistoryPrefs", Context.MODE_PRIVATE)
        val historySet = sharedPreferences.getStringSet("historyList", setOf())
        return historySet?.toList() ?: emptyList()
    }

    private fun saveHistoryData(historyDataList: List<String>) {
        val sharedPreferences = getSharedPreferences("HistoryPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putStringSet("historyList", historyDataList.toSet())
        editor.apply()
    }

    private fun btnBack() {
        binding.ivBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onHistoryItemClick(historyData: String) {
        val position = historyDataList.indexOf(historyData)
        if (position != -1) {
            historyDataList.removeAt(position)
            historyAdapter.notifyItemRemoved(position)
            saveHistoryData(historyDataList)
        }
    }

    @Suppress("DEPRECATION")
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}