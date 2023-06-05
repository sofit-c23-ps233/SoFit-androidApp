package com.capstone.sofitapp.ui.quisioner


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.sofitapp.databinding.FragmentQuisioner2Binding


class Quisioner2Fragment : Fragment() {

    private var _binding: FragmentQuisioner2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuisioner2Binding.inflate(inflater, container, false)
        return binding.root
    }
}