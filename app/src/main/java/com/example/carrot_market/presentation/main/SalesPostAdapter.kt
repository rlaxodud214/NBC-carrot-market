package com.example.carrot_market.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carrot_market.util.setHeartIcon
import com.example.carrot_market.data.model.SalesPost
import com.example.carrot_market.databinding.ItemRvPostsBinding
import com.example.carrot_market.Util

class SalesPostAdapter(
    private val onClick: (Int) -> Unit,
    private val onLongClick: (Int) -> Boolean,
) : RecyclerView.Adapter<SalesPostAdapter.ViewHolder>() {

    var salesPosts: List<SalesPost> = listOf()

    inner class ViewHolder(val binding: ItemRvPostsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            with(itemView) {
                setOnClickListener { onClick(adapterPosition) }
                setOnLongClickListener { onLongClick(adapterPosition) }
            }
        }

        fun bind(salesPost: SalesPost) = with(binding) {
        with(salesPost) {
            ivProductImage.setImageResource(product.image)
            tvProductName.text = product.name
            tvAddress.text = user.address
            tvProductPrice.text = Util.dec.format(product.price)
            tvChatCount.text = post.chatCount.toString()
            ivLike.setHeartIcon(post.isLikeActivate)
            tvLikeCount.text = (post.likeCount + if(post.isLikeActivate) 1 else 0).toString()
        }
    }
}

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val binding = ItemRvPostsBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )

    return ViewHolder(binding)
}

override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val data = salesPosts[position]
    holder.bind(data)
}

override fun getItemCount() = salesPosts.size
}