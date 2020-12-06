package com.example.intervalcalculator.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.intervalcalculator.R
import com.example.intervalcalculator.databinding.FragmentPart1Binding
import com.example.intervalcalculator.ui.ResultItem
import com.example.intervalcalculator.utils.IntervalUtils
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class Part1Fragment : Fragment(R.layout.fragment_part1) {

    private lateinit var binding: FragmentPart1Binding
    private var utils: IntervalUtils = IntervalUtils()
    private var adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPart1Binding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnFindSolution.setOnClickListener {
            findSolution()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun findSolution() {
        var a0: Double
        var a1: Double
        var b0: Double
        var b1: Double
        // todo input validation
        with(binding) {
            a0 = etInt1Start.text.toString().toDouble()
            a1 = etInt1End.text.toString().toDouble()
            b0 = etInt2Start.text.toString().toDouble()
            b1 = etInt2End.text.toString().toDouble()
            val intervalA = Pair(a0, a1)
            val intervalB = Pair(b0, b1)
            adapter.add(ResultItem("A + B", utils.add(intervalA, intervalB)))
            adapter.add(ResultItem("A - B", utils.subtract(intervalA, intervalB)))
            adapter.add(ResultItem("A x B", utils.multiply(intervalA, intervalB)))
            adapter.add(ResultItem("A / B", utils.divide(intervalA, intervalB)))
            recyclerViewResult.adapter = adapter
        }
    }
}