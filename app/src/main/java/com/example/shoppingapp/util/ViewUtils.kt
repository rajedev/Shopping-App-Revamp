package com.example.shoppingapp.util

import android.content.Context
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat

fun AppCompatTextView.setUi(title: String, context: Context, colorId: Int) {
    text = title
    setTextColor(ContextCompat.getColor(context, colorId))
}