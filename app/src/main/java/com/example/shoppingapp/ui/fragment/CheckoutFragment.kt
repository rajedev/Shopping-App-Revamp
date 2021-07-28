package com.example.shoppingapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.shoppingapp.databinding.FragmentCheckoutBinding

class CheckoutFragment : Fragment() {

    private var _binding: FragmentCheckoutBinding? = null
    private val binding get() = _binding!!
    private val args: CheckoutFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        binding.apply {
            productNameFc.text = args.cartItem.title
            productPriceFc.text = "Price : ₹" + args.cartItem.price.toString()
            categoryFc.text = "Category : " + args.cartItem.category

            checkoutBtn.setOnClickListener {
                args.cartItem.isPlaced = true
                Toast.makeText(requireContext(), "Order has been placed", Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressed()
            }
        }

        val itemImage = args.cartItem.image
        Glide.with(this).load(itemImage).into(binding.productImageFc)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}