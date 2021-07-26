package com.example.shoppingapp.repository

import com.example.shoppingapp.network.RetrofitService

class StoreRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllProducts() = retrofitService.getAllProducts()
}