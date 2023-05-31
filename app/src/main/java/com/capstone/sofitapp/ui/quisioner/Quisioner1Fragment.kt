package com.capstone.sofitapp.ui.quisioner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.sofitapp.R
import com.capstone.sofitapp.databinding.FragmentQuisioner1Binding

class Quisioner1Fragment : Fragment() {

    private var _binding: FragmentQuisioner1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuisioner1Binding.inflate(inflater, container, false)
        return binding.root
    }

}