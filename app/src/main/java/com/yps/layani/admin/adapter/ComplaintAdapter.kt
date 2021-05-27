package com.yps.layani.admin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yps.layani.admin.model.Complaint
import com.yps.layani.databinding.ListItemBinding

class ComplaintAdapter(private val listKomplain: List<Complaint>) :
    RecyclerView.Adapter<ComplaintAdapter.ItemViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Complaint)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ItemViewHolder {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = listKomplain.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.apply {
            tvNama.text = listKomplain[position].username
            tvComplaint.text = listKomplain[position].tweet

            root.setOnClickListener { onItemClickCallback.onItemClicked(listKomplain[holder.adapterPosition]) }
        }
    }

     class ItemViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)
}