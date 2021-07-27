package com.example.shoppingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppingapp.databinding.ItemProductBinding
import com.example.shoppingapp.model.Store

class StoreAdapter(
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {

    var products = mutableListOf<Store>()
    lateinit var context: Context

    fun setProductList(products: List<Store>) {
        this.products = products.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        context = parent.context
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bindView(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class StoreViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bindView(store: Store) {
            binding.apply {
                productTv.text = store.title
                productPrice.text = "₹" + store.price.toString()
                productCategoryTv.text = store.category
                Glide.with(context).load(store.image).into(productIv)
            }
        }

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val currentPos = adapterPosition
            listener.onItemClick(products[currentPos])
        }

    }

    interface OnItemClickListener {
        fun onItemClick(product: Store)
    }
}