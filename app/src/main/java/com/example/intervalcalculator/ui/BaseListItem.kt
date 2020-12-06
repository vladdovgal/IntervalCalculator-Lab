package com.example.intervalcalculator.ui

import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.xwray.groupie.viewbinding.BindableItem

abstract class BaseListItem<T : ViewBinding>(
    @LayoutRes private val layoutRes: Int
) : BindableItem<T>() {
    override fun getLayout(): Int = layoutRes
}
