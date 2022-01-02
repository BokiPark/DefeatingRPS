package com.boki.park.defeatingrps.ui.ranking

import androidx.recyclerview.widget.RecyclerView
import com.boki.park.defeatingrps.databinding.ItemRankingBinding
import com.boki.park.defeatingrps.repo.data.Ranking

class RankingViewHolder(
    private val binding: ItemRankingBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(ranking: Ranking) { binding.ranking = ranking }
}