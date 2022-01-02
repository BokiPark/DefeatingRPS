package com.boki.park.defeatingrps.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.boki.park.defeatingrps.R
import com.boki.park.defeatingrps.databinding.FragmentMainBinding

class MainFragment: Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false).apply {
            btnStart.setOnClickListener { findNavController().navigate(R.id.main_to_game) }
            btnRanking.setOnClickListener { findNavController().navigate(R.id.main_to_ranking) }
            btnExit.setOnClickListener { activity?.finish() }
        }

        return binding.root
    }

}