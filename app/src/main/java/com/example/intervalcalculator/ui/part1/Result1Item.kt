package com.example.intervalcalculator.ui.part1

import android.view.View
import com.example.intervalcalculator.R
import com.example.intervalcalculator.databinding.ListItemResultBinding
import com.example.intervalcalculator.ui.BaseListItem

class Result1Item(private val title: String, private val resultingInterval: Pair<Double, Double>) :
    BaseListItem<ListItemResultBinding>(R.layout.list_item_result) {
    override fun bind(viewBinding: ListItemResultBinding, position: Int) {
        with(viewBinding) {
            result.text = "$title = [ ${resultingInterval.first} ; ${resultingInterval.second} ]"
        }
    }


    override fun initializeViewBinding(view: View): ListItemResultBinding =
        ListItemResultBinding.bind(view)
}