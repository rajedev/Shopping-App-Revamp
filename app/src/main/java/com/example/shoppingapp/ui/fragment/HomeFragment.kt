package com.example.shoppingapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.adapter.StoreAdapter
import com.example.shoppingapp.databinding.FragmentHomeBinding
import com.example.shoppingapp.network.RetrofitService
import com.example.shoppingapp.repository.StoreRepository
import com.example.shoppingapp.viewmodel.StoreViewModel
import com.example.shoppingapp.viewmodel.StoreViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: StoreViewModel
    private val retrofitService = RetrofitService.getInstance()
    private val myAdapter = StoreAdapter()
    private val TAG = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, StoreViewModelFactory(StoreRepository(retrofitService))).get(StoreViewModel::class.java)

        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = myAdapter
        }

        viewModel.productList.observe(requireActivity(), Observer {
            Log.d(TAG, "onCreateView: $it")
            myAdapter.setProductList(it)
        })

        viewModel.errorMessage.observe(requireActivity(), Observer {  })
        viewModel.getAllProducts()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}