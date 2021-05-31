package com.yps.layani.admin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yps.layani.admin.model.Complaint
import com.yps.layani.databinding.ListItemBinding

class ComplaintAdapter : RecyclerView.Adapter<ComplaintAdapter.CardViewViewHolder>() {

    private var listUsers = ArrayList<Complaint>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setData(dataUser: List<Complaint>) {
        listUsers.clear()
        listUsers.addAll(dataUser)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewViewHolder(binding)
    }

    override fun getItemCount(): Int = listUsers.size
    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listUsers[position])
    }

    inner class CardViewViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Complaint) {
            with(binding) {
                tvNama.text = user.username
                tvComplaint.text = user.body

                itemView.setOnClickListener {
                    onItemClickCallback?.onItemClicked(user)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Complaint)
    }
}