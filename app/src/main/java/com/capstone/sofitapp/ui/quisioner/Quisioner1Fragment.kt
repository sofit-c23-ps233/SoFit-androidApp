package com.capstone.sofitapp.ui.quisioner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.capstone.sofitapp.databinding.FragmentQuisioner1Binding

class Quisioner1Fragment : Fragment() {

    private var _binding: FragmentQuisioner1Binding? = null
    private val binding get() = _binding!!
    private val viewModel : QuisionerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuisioner1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rbMen.setOnClickListener{
            viewModel.gender = "Laki-laki"
        }
        binding.rbWomen.setOnClickListener{
            viewModel.gender = "Perempuan"
        }
    }

}