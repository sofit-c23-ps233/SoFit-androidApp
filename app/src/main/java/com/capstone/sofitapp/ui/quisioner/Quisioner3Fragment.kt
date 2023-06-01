package com.capstone.sofitapp.ui.quisioner


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.sofitapp.databinding.FragmentQuisioner3Binding


class Quisioner3Fragment : Fragment() {

    private var _binding: FragmentQuisioner3Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuisioner3Binding.inflate(inflater, container, false)
        return binding.root
    }
}