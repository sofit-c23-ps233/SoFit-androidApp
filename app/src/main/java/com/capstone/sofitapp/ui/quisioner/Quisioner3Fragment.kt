package com.capstone.sofitapp.ui.quisioner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.capstone.sofitapp.databinding.FragmentQuisioner3Binding


class Quisioner3Fragment : Fragment() {

    private var _binding: FragmentQuisioner3Binding? = null
    private val binding get() = _binding!!
    private val viewModel : QuisionerViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuisioner3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // listen to edit text value changes
        binding.editBerat.addTextChangedListener {
            viewModel.weight = it.toString()
        }
    }
}