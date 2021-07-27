package com.example.shoppingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.databinding.CartItemBinding
import com.example.shoppingapp.model.Store

class CartAdapter: RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var cartItems = mutableListOf<Store>()

    fun setCartList(cartItems: List<Store>) {
        this.cartItems = cartItems.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
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

    inner class CartViewHolder(private val binding: CartItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindView(store: Store) {
            binding.apply {
                cartItemTitle.text = store.title
            }
        }

    }

}