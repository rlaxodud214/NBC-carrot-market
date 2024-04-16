package com.example.carrot_market.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carrot_market.data.Post
import com.example.carrot_market.databinding.ItemRvPostsBinding
import com.example.carrot_market.Util

class PostAdapter(
    val posts: List<Post>,
    private val onClick: (Int) -> Unit,
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRvPostsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onClick(adapterPosition)
            }
        }

        fun bind(data: Post) {
            with(binding) {
                ivProductImage.setImageResource(data.image)
                tvProductName.text = data.name
                tvAddress.text = data.address
                tvProductPrice.text = Util.dec.format(data.price)
                tvChatCount.text = data.chatCount.toString()
                tvLikeCount.text = data.likeCount.toString()
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
        val data = posts[position]
        holder.bind(data)
    }

    override fun getItemCount() = posts.size
}