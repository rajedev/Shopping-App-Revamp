package com.example.shoppingapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.shoppingapp.databinding.FragmentProductBinding
import com.example.shoppingapp.model.Store
import com.example.shoppingapp.viewmodel.SharedViewModel

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!
    private val args: ProductFragmentArgs by navArgs()

    companion object {
        private var cartItems: MutableList<Store> = mutableListOf()
        private var currentProductId = -1
    }

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val TAG = "ProductFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)

        binding.apply {
            productNameFp.text = args.product.title
            productCategoryFp.text = args.product.category
            productPriceFp.text = "₹" + args.product.price.toString()
            productDescFp.text = args.product.description

            addToCartBtn.setOnClickListener {
                if (args.product.id != currentProductId) {
                    cartItems.add(sharedViewModel.incrementCount(), args.product)
                    sharedViewModel.addCartItem(cartItems)
                    Log.d(TAG, "onCreateView: $cartItems")
                    currentProductId = args.product.id
                    Toast.makeText(requireContext(), "Added to the Cart", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Product already added", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
        val imageUrl = args.product.image
        Glide.with(this).load(imageUrl).into(binding.productImageFp)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}