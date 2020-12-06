package com.example.intervalcalculator.ui.part1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.intervalcalculator.R
import com.example.intervalcalculator.databinding.FragmentPart1Binding
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
        setUpListeners()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpListeners() {
        binding.btnFindSolution.setOnClickListener {
            findSolution()
        }
        binding.btnClear.setOnClickListener {
            clearInputFields()
        }
    }

    private fun clearInputFields() {
        with(binding) {
            etInt1Start.text = null
            etInt2Start.text = null
            etInt1End.text = null
            etInt2End.text = null
        }
    }

    private fun findSolution() {
        var a0: Double
        var a1: Double
        var b0: Double
        var b1: Double
        with(binding) {
            if (isInputValid()) {
                a0 = etInt1Start.text.toString().toDouble()
                a1 = etInt1End.text.toString().toDouble()
                b0 = etInt2Start.text.toString().toDouble()
                b1 = etInt2End.text.toString().toDouble()
                val intervalA = Pair(a0, a1)
                val intervalB = Pair(b0, b1)
                adapter.clear()
                adapter.add(Result1Item("A + B", utils.add(intervalA, intervalB)))
                adapter.add(Result1Item("A - B", utils.subtract(intervalA, intervalB)))
                adapter.add(Result1Item("A x B", utils.multiply(intervalA, intervalB)))
                adapter.add(Result1Item("A / B", utils.divide(intervalA, intervalB)))
                recyclerViewResult.adapter = adapter
            }
        }
    }

    private fun isInputValid(): Boolean {
        val emptyError = "Can't be empty"
        with(binding) {
            return when {
                etInt1Start.text.isEmpty() -> {
                    etInt1Start.error = emptyError
                    false
                }
                etInt2Start.text.isEmpty() -> {
                    etInt2Start.error = emptyError
                    false
                }
                etInt1End.text.isEmpty() -> {
                    etInt1End.error = emptyError
                    false
                }
                etInt2End.text.isEmpty() -> {
                    etInt2End.error = emptyError
                    false
                }
                etInt1Start.text.toString().toDouble() > etInt1End.text.toString().toDouble() -> {
                    etInt1Start.error = "a0 must be <= a1"
                    false
                }
                etInt2Start.text.toString().toDouble() > etInt2End.text.toString().toDouble() -> {
                    etInt2Start.error = "b0 must be <= b1"
                    false
                }
                etInt2Start.text.toString().toDouble() == 0.0 || etInt2End.text.toString()
                    .toDouble() == 0.0 -> {
                    Toast.makeText(
                        context,
                        "Can't divide by interval with 0 edge",
                        Toast.LENGTH_LONG
                    ).show()
                    false
                }
                else -> {
                    etInt1Start.error = null
                    etInt2Start.error = null
                    etInt2End.error = null
                    etInt1End.error = null
                    true
                }
            }
        }
    }
}