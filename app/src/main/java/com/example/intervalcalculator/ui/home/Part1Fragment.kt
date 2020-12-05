package com.example.intervalcalculator.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.intervalcalculator.R
import com.example.intervalcalculator.databinding.FragmentPart1Binding
import com.example.intervalcalculator.utils.IntervalUtils

const val LOG_TAG = "myLogs"
class Part1Fragment : Fragment(R.layout.fragment_part1) {

    private lateinit var binding: FragmentPart1Binding
    private var utils: IntervalUtils = IntervalUtils()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPart1Binding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnFindSolution.setOnClickListener {
            Log.d(LOG_TAG, "chck" )
            findSolution()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun findSolution() {
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
        val intervalA = Pair(a0, a1)
        val intervalB = Pair(b0, b1)
        Log.d(LOG_TAG, "A + B = ${utils.add(intervalA, intervalB)}" )
        Log.d(LOG_TAG, "A - B = ${utils.subtract(intervalA, intervalB)}" )
        Log.d(LOG_TAG, "A * B = ${utils.multiply(intervalA, intervalB)}" )
        Log.d(LOG_TAG, "A / B = ${utils.divide(intervalA, intervalB)}" )
    }
}