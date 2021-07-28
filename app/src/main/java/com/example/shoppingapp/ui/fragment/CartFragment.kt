package com.example.shoppingapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.adapter.CartAdapter
import com.example.shoppingapp.databinding.FragmentCartBinding
import com.example.shoppingapp.model.Store
import com.example.shoppingapp.viewmodel.SharedViewModel

class CartFragment : Fragment(), CartAdapter.OnCartItemClickListener {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val cartAdapter = CartAdapter(this)
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)

        binding.rvCartFragment.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartAdapter
        }

        sharedViewModel.selectedItem.observe(requireActivity(), Observer {
            cartAdapter.setCartList(it)
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCartItemClick(cart: Store) {
        if (!cart.isPlaced) {
            val action = CartFragmentDirections.actionCartFragmentToCheckoutFragment(cart)
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Order already placed", Toast.LENGTH_SHORT).show()
        }
    }
}