package com.example.intervalcalculator.ui.part1

import android.annotation.SuppressLint
import android.view.View
import com.example.intervalcalculator.R
import com.example.intervalcalculator.databinding.ListItemResultBinding
import com.example.intervalcalculator.ui.BaseListItem
import com.example.intervalcalculator.utils.round

class Result1Item(private val title: String, private val resultingInterval: Pair<Double, Double>) :
    BaseListItem<ListItemResultBinding>(R.layout.list_item_result) {
    @SuppressLint("SetTextI18n")
    override fun bind(viewBinding: ListItemResultBinding, position: Int) {
        with(viewBinding) {
            result.text =
                "$title = " +
                        "[ ${resultingInterval.first.round(2)} ;" +
                        " ${resultingInterval.second.round(2)} ]"
        }
    }


    override fun initializeViewBinding(view: View): ListItemResultBinding =
        ListItemResultBinding.bind(view)
}