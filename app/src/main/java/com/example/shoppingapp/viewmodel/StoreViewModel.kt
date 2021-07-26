package com.example.shoppingapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppingapp.model.Store
import com.example.shoppingapp.repository.StoreRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreViewModel constructor(private val repository: StoreRepository) : ViewModel() {

    val productList = MutableLiveData<List<Store>>()
    val errorMessage = MutableLiveData<String>()
    private val TAG = "StoreViewModel"

    fun getAllProducts() {
        val response = repository.getAllProducts()

        response.enqueue(object : Callback<List<Store>>{
            override fun onResponse(call: Call<List<Store>>, response: Response<List<Store>>) {
                productList.postValue(response.body())
                Log.d(TAG, "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<List<Store>>, t: Throwable) {
                errorMessage.postValue(t.message)
                Log.d(TAG, "onFailure: ${t.message}")
            }

        })
    }

}