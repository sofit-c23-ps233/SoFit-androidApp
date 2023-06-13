package com.capstone.sofitapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.capstone.sofitapp.R
import com.capstone.sofitapp.data.model.ViewModelFactory
import com.capstone.sofitapp.databinding.ActivityMainBinding
import com.capstone.sofitapp.ui.WelcomeActivity
import com.capstone.sofitapp.ui.history.HistoryActivity
import com.capstone.sofitapp.ui.profile.ProfileActivity
//import com.capstone.sofitapp.ui.quisioner.QuisionerActivity
import com.capstone.sofitapp.ui.quisioner.QuisionerActivity
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var factory: ViewModelFactory
    private var token = ""
    private val mainViewModel by viewModels<MainViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUser()
        setupViewModel()

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        binding.homeActivity.btnNavigation.setOnClickListener {
            drawerLayout.openDrawer(navView)
        }

        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_test -> {
                    val intent = Intent(this, QuisionerActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_history -> {
                    val intent = Intent(this, HistoryActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }

        binding.homeActivity.btnEdit.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        binding.homeActivity.cvTest.setOnClickListener {
            val intent = Intent(this, QuisionerActivity::class.java)
            startActivity(intent)
        }

        binding.homeActivity.cvHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        binding.navLogout.setOnClickListener {
            startActivity(Intent(this, WelcomeActivity::class.java))
            mainViewModel.logout()
        }

        // Navigation Drawer Back Button
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    return
                }
                finish()
            }
        })
    }

    private fun setupUser() {
        mainViewModel.getSession().observe(this@MainActivity) {
            token = it.name
            if (!it.isLogin) {
                moveActivity()
            }
            else {
                val userName = it.name
                binding.homeActivity.tvUser.text = userName
            }
        }
        showToast()
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }


    private fun moveActivity() {
        startActivity(Intent(this@MainActivity, WelcomeActivity::class.java))
        finish()
    }

    private fun showToast() {
        mainViewModel.toastText.observe(this@MainActivity) {
            it.getContentIfNotHandled()?.let { toastText ->
                Toast.makeText(
                    this@MainActivity, toastText, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val backIntent = Intent(Intent.ACTION_MAIN)
        backIntent.addCategory(Intent.CATEGORY_HOME)
        backIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(backIntent)
    }

}