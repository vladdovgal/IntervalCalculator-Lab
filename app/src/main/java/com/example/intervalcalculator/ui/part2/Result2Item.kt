package com.example.intervalcalculator.ui.part2

import android.view.View
import com.example.intervalcalculator.R
import com.example.intervalcalculator.databinding.ListItemResultBinding
import com.example.intervalcalculator.ui.BaseListItem

class Result2Item(private val title: String, private val resultValue: Double) :
    BaseListItem<ListItemResultBinding>(R.layout.list_item_result) {
    override fun bind(viewBinding: ListItemResultBinding, position: Int) {
        with(viewBinding) {
            result.text = "$title = $resultValue "
        }
    }


    override fun initializeViewBinding(view: View): ListItemResultBinding =
        ListItemResultBinding.bind(view)
}