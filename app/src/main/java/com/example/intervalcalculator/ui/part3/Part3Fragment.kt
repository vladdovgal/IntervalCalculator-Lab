package com.example.intervalcalculator.ui.part3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.intervalcalculator.R
import com.example.intervalcalculator.databinding.FragmentPart1Binding
import com.example.intervalcalculator.databinding.FragmentPart3Binding
import com.example.intervalcalculator.utils.IntervalUtils
import com.example.intervalcalculator.utils.Matrix
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import java.lang.IllegalArgumentException
import javax.xml.transform.dom.DOMLocator

class Part3Fragment : Fragment(R.layout.fragment_part3) {
    private lateinit var binding: FragmentPart3Binding
    private var utils: IntervalUtils = IntervalUtils()
    private var adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPart3Binding.inflate(layoutInflater)
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

    private fun findSolution() {
        var a0: Double
        var a1: Double
        var b0: Double
        var b1: Double
        var c0: Double
        var c1: Double
        var d0: Double
        var d1: Double
        var b10: Double
        var b11: Double
        var b20: Double
        var b21: Double
        with(binding) {
            if (isInputValid()) {
                a0 = etA0.text.toString().toDouble()
                a1 = etA1.text.toString().toDouble()
                b0 = etB0.text.toString().toDouble()
                b1 = etB1.text.toString().toDouble()
                c0 = etC0.text.toString().toDouble()
                c1 = etC1.text.toString().toDouble()
                d0 = etD0.text.toString().toDouble()
                d1 = etD1.text.toString().toDouble()
                b10 = etB10.text.toString().toDouble()
                b11 = etB11.text.toString().toDouble()
                b20 = etB20.text.toString().toDouble()
                b21 = etB21.text.toString().toDouble()
                val a = Pair(a0, a1)
                val b = Pair(b0, b1)
                val c = Pair(c0, c1)
                val d = Pair(d0, d1)
                val _b1 = Pair(b10, b11)
                val _b2 = Pair(b20, b21)
                var resultMatrix : Matrix = listOf(listOf())
                try {
                    resultMatrix = utils.findXMatrix(a, b, c, d, _b1, _b2)
                    val xValues = listOf(resultMatrix[0][0].first,resultMatrix[0][0].second, resultMatrix[1][0].first, resultMatrix[1][0].second)
                    adapter.clear()
                    adapter.add(Result3Item(xValues))
                    recyclerViewResult.adapter = adapter
                } catch (e: IllegalArgumentException) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }

            }
        }

    }

    private fun clearInputFields() {
        with(binding) {
            etA0.text = null
            etA1.text = null
            etB0.text = null
            etB1.text = null
            etC0.text = null
            etC1.text = null
            etD0.text = null
            etD1.text = null
            etB10.text = null
            etB11.text = null
            etB20.text = null
            etB21.text = null
        }
    }

    private fun isInputValid(): Boolean {
        val emptyError = "Can't be empty"
        with(binding) {
            return when {
                etA0.text.isEmpty() -> {
                    etA0.error = emptyError
                    false
                }
                etA1.text.isEmpty() -> {
                    etA1.error = emptyError
                    false
                }
                etB0.text.isEmpty() -> {
                    etB0.error = emptyError
                    false
                }
                etB1.text.isEmpty() -> {
                    etB1.error = emptyError
                    false
                }
                etC0.text.isEmpty() -> {
                    etC0.error = emptyError
                    false
                }
                etC1.text.isEmpty() -> {
                    etC0.error = emptyError
                    false
                }
                etD0.text.isEmpty() -> {
                    etD0.error = emptyError
                    false
                }
                etD1.text.isEmpty() -> {
                    etD1.error = emptyError
                    false
                }
                etB10.text.isEmpty() -> {
                    etB10.error = emptyError
                    false
                }
                etB11.text.isEmpty() -> {
                    etB11.error = emptyError
                    false
                }
                etB20.text.isEmpty() -> {
                    etB20.error = emptyError
                    false
                }
                etB21.text.isEmpty() -> {
                    etB21.error = emptyError
                    false
                }
                etA0.text.toString().toDouble() > etA1.text.toString().toDouble() -> {
                    etB0.error = "a0 must be <= a1"
                    false
                }
                etB0.text.toString().toDouble() > etB1.text.toString().toDouble() -> {
                    etB0.error = "b0 must be <= b1"
                    false
                }
                etC0.text.toString().toDouble() > etC1.text.toString().toDouble() -> {
                    etC0.error = "c0 must be <= c1"
                    false
                }
                etD0.text.toString().toDouble() > etD1.text.toString().toDouble() -> {
                    etD0.error = "d0 must be <= d1"
                    false
                }
                etB10.text.toString().toDouble() > etB11.text.toString().toDouble() -> {
                    etB10.error = "b10 must be <= b11"
                    false
                }
                etB20.text.toString().toDouble() > etB21.text.toString().toDouble() -> {
                    etB10.error = "b20 must be <= b21"
                    false
                }
                else -> {
                    true
                }
            }
        }
    }

}