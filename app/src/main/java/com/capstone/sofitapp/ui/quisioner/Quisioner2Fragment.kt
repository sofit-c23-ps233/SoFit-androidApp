package com.capstone.sofitapp.ui.quisioner


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.capstone.sofitapp.databinding.FragmentQuisioner2Binding


class Quisioner2Fragment : Fragment() {

    private var _binding: FragmentQuisioner2Binding? = null
    private val binding get() = _binding!!
    val viewModel : QuisionerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuisioner2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // listen to edit text value changes
        binding.editTinggi.addTextChangedListener {
            viewModel.height = it.toString()
        }
    }
}