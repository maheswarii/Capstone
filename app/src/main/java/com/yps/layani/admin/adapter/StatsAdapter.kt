package com.yps.layani.admin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yps.layani.admin.model.Stats
import com.yps.layani.databinding.ListLeaderBinding

class StatsAdapter : RecyclerView.Adapter<StatsAdapter.CardViewViewHolder>(){

    private var listStats = ArrayList<Stats>()
    //private var onItemClickCallback: OnItemClickCallback? = null

    fun setData(dataStats: List<Stats>) {
        listStats.clear()
        listStats.addAll(dataStats)
        notifyDataSetChanged()
    }

//    fun setOnItemClickCallback(onItemClickCallback: ComplaintAdapter.OnItemClickCallback) {
//        this.onItemClickCallback = onItemClickCallback
//    }

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
        fun bind(stats: Stats) {
            with(binding) {
                tvName.text = stats.name
                tvExp.text = stats.exp.toString()
                tvRank.text = stats.rank

//                itemView.setOnClickListener {
//                    onItemClickCallback?.onItemClicked(stats)
//                }
            }
        }
    }

//    interface OnItemClickCallback {
//        fun onItemClicked(data: Stats)
//    }
}
