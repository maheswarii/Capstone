package com.yps.layani.admin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yps.layani.R
import com.yps.layani.admin.model.Information

class ListInfoAdapter (private val listInfo: ArrayList<Information>) :
    RecyclerView.Adapter<ListInfoAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Information)
    }


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tv_name_info)
        var ivInfotimg: ImageView = itemView.findViewById(R.id.iv_img_info)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_information, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listInfo.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val event = listInfo[position]
        Glide.with(holder.itemView.context)
            .load(event.photo)
            .into(holder.ivInfotimg)
        holder.tvTitle.text = event.title

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listInfo[holder.adapterPosition]) }
    }
}