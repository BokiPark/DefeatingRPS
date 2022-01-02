package com.boki.park.defeatingrps.ui.ranking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.boki.park.defeatingrps.databinding.ItemRankingBinding
import com.boki.park.defeatingrps.repo.data.Ranking

class RankingAdapter: ListAdapter<Ranking, RankingViewHolder>(object: DiffUtil.ItemCallback<Ranking>() {
    override fun areItemsTheSame(oldItem: Ranking, newItem: Ranking) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Ranking, newItem: Ranking) = oldItem == newItem
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RankingViewHolder(
            ItemRankingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}