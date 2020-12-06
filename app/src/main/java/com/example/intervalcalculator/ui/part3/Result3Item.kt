package com.example.intervalcalculator.ui.part3

import android.view.View
import com.example.intervalcalculator.R
import com.example.intervalcalculator.databinding.ListItemResult3Binding
import com.example.intervalcalculator.databinding.ListItemResultBinding
import com.example.intervalcalculator.ui.BaseListItem
import com.example.intervalcalculator.utils.round

class Result3Item(private val values: List<Double>) :
    BaseListItem<ListItemResult3Binding>(R.layout.list_item_result3) {
    override fun bind(viewBinding: ListItemResult3Binding, position: Int) {
        with(viewBinding) {
            x00.text = values[0].round(3).toString()
            x01.text = values[1].round(3).toString()
            x10.text = values[2].round(3).toString()
            x11.text = values[3].round(3).toString()
        }
    }


    override fun initializeViewBinding(view: View): ListItemResult3Binding =
        ListItemResult3Binding.bind(view)
}