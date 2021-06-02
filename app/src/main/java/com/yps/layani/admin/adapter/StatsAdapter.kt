package com.yps.layani.admin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yps.layani.admin.model.User
import com.yps.layani.databinding.ListLeaderBinding

class StatsAdapter : RecyclerView.Adapter<StatsAdapter.CardViewViewHolder>(){

    private var listStats = ArrayList<User>()

    fun setData(dataStats: List<User>) {
        listStats.clear()
        listStats.addAll(dataStats)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: StatsAdapter.CardViewViewHolder, position: Int) {
        holder.bind(listStats[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsAdapter.CardViewViewHolder {
        val binding =
            ListLeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewViewHolder(binding)
    }

    override fun getItemCount(): Int = listStats.size

    inner class CardViewViewHolder(private val binding: ListLeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(stats: User) {
            with(binding) {
                tvName.text = stats.name
                tvExp.text = stats.exp.toString()
                tvRank.text = stats.rank
            }
        }
    }
}
