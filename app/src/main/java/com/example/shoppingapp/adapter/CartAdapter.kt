package com.example.shoppingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppingapp.R
import com.example.shoppingapp.databinding.CartItemBinding
import com.example.shoppingapp.model.Store
import com.example.shoppingapp.util.setUi

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var cartItems = mutableListOf<Store>()
    private lateinit var context: Context

    fun setCartList(cartItems: List<Store>) {
        this.cartItems = cartItems.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        context = parent.context
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bindView(cartItems[position])
    }

    override fun getItemCount(): Int {
        return if (cartItems.isEmpty()) {
            0
        } else {
            cartItems.size
        }
    }

    inner class CartViewHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(store: Store) {
            binding.apply {
                cartItemTitle.text = store.title
                cartItemPrice.text = "₹" + store.price.toString()
                Glide.with(context).load(store.image).into(cartItemIv)
            }

            if (store.isPlaced) {
                binding.placeOrderBtn.setUi("Order Placed", context, R.color.yellow)
            } else {
                binding.placeOrderBtn.text = "Place Order"
            }
            binding.placeOrderBtn.setOnClickListener {
                if (!store.isPlaced) {
                    store.isPlaced = true
                    Toast.makeText(context, "Order has been Placed", Toast.LENGTH_SHORT).show()
                    notifyDataSetChanged()
                }
            }
        }

    }

}