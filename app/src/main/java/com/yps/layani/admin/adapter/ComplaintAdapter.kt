package com.yps.layani.admin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yps.layani.R
import com.yps.layani.admin.model.Complaint
import com.yps.layani.admin.model.Information
import com.yps.layani.databinding.ListItemBinding

class ComplaintAdapter(private val listKomplain: ArrayList<Complaint>) :
    RecyclerView.Adapter<ComplaintAdapter.ItemViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Complaint)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ComplaintAdapter.ItemViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item, viewGroup, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val komp = listKomplain[position]
        holder.name.text = komp.name
        holder.komplain.text = komp.complaint

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listKomplain[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return listKomplain.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tvNama)
        var komplain: TextView = itemView.findViewById(R.id.tvComplaint)
    }
}