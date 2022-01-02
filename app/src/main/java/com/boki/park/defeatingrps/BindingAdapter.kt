package com.boki.park.defeatingrps

import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("bind:textBeforeColon", "bind:numAfterColon")
    fun TextView.setTextColonNum(textBeforeColon: String, numAfterColon: Int) {
        text = ("$textBeforeColon : $numAfterColon")
    }
}