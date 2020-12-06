package com.example.intervalcalculator.ui.part2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.intervalcalculator.databinding.FragmentPart2Binding
import com.example.intervalcalculator.ui.part1.Result1Item
import com.example.intervalcalculator.utils.IntervalUtils
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class Part2Fragment : Fragment() {

    private lateinit var binding: FragmentPart2Binding
    private var utils: IntervalUtils = IntervalUtils()
    private var adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPart2Binding.inflate(layoutInflater)
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
            etInt1End.text = null
        }
    }

    private fun findSolution() {
        var a0: Double
        var a1: Double
        with(binding) {
            if (isInputValid()) {
                a0 = etInt1Start.text.toString().toDouble()
                a1 = etInt1End.text.toString().toDouble()
                val interval = Pair(a0, a1)
                adapter.clear()
                adapter.add(Result2Item("wid A", utils.calcWidth(interval)))
                adapter.add(Result2Item("mid A", utils.calcMid(interval)))
                adapter.add(Result2Item("|A|", utils.calcAbsValue(interval)))
                adapter.add(Result2Item("rad A", utils.calcRadius(interval)))
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
                etInt1End.text.isEmpty() -> {
                    etInt1End.error = emptyError
                    false
                }
                etInt1Start.text.toString().toDouble() > etInt1End.text.toString().toDouble() -> {
                    etInt1Start.error = "a0 must be <= a1"
                    false
                }
                else -> {
                    etInt1Start.error = null
                    etInt1End.error = null
                    true
                }
            }
        }
    }
}