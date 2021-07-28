package com.example.shoppingapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingapp.model.Store

class SharedViewModel : ViewModel() {

    val selectedItem = MutableLiveData<List<Store>>()
    var count = 0

    fun addCartItem(cartItems: List<Store>) {
        selectedItem.value = cartItems
    }

    fun incrementCount(): Int {
        return count++
    }
}