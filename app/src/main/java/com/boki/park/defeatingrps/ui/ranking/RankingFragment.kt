package com.boki.park.defeatingrps.ui.ranking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.boki.park.defeatingrps.databinding.FragmentRankingBinding

class RankingFragment: Fragment() {

    private lateinit var binding: FragmentRankingBinding
    private val adapter by lazy { RankingAdapter() }
    private val viewModel by viewModels<RankingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRankingBinding.inflate(inflater, container, false)
        binding.listRanking.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.recordList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }
}