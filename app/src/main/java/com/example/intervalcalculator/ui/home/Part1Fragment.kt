package com.example.intervalcalculator.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.intervalcalculator.R
import com.example.intervalcalculator.databinding.FragmentPart1Binding

class Part1Fragment : Fragment() {

    private lateinit var binding: FragmentPart1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPart1Binding.inflate(layoutInflater)
        val root = inflater.inflate(R.layout.fragment_part1, container, false)
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUI()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initUI() {
        var a0 = 0.0
        var a1 = 0.0
        var b0 = 0.0
        var b1 = 0.0
        with(binding) {
            a0 = etInt1Start.text.toString().toDouble()
            a1 = etInt1End.text.toString().toDouble()
            b0 = etInt2Start.text.toString().toDouble()
            b1 = etInt2End.text.toString().toDouble()
        }
    }
}