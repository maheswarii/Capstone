package com.yps.layani.admin.ui.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yps.layani.R
import com.yps.layani.admin.model.Notifications

class NotificationsAdapter (private val listNotif: ArrayList<Notifications>) : RecyclerView.Adapter<NotificationsAdapter.ItemViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Notifications)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): NotificationsAdapter.ItemViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_notif, viewGroup, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val notif = listNotif[position]
        holder.pesan.text = notif.pesan


        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listNotif[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return listNotif.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pesan: TextView = itemView.findViewById(R.id.tv_notif)
    }
}