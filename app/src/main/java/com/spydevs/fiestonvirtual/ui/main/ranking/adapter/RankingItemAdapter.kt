package com.spydevs.fiestonvirtual.ui.main.ranking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spydevs.fiestonvirtual.R
import com.spydevs.fiestonvirtual.domain.models.ranking.Ranking

class RankingItemAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var rankings: List<Ranking> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RankingItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_ranking, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RankingItemViewHolder).bind(rankings[position])
    }

    override fun getItemCount(): Int {
        return rankings.size
    }

    fun addData(rankings: List<Ranking>) {
        this.rankings = rankings
        notifyDataSetChanged()
    }
}