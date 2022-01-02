package com.boki.park.defeatingrps.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.boki.park.defeatingrps.R
import com.boki.park.defeatingrps.databinding.FragmentGameBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameFragment: Fragment() {

    private lateinit var binding: FragmentGameBinding
    private val viewModel by viewModels<GameViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.btnRock.setOnClickListener { viewModel.setMyRPS(GameViewModel.RPS.ROCK) }
        binding.btnScissors.setOnClickListener { viewModel.setMyRPS(GameViewModel.RPS.SCISSORS) }
        binding.btnPaper.setOnClickListener { viewModel.setMyRPS(GameViewModel.RPS.PAPER) }
        binding.btnRestart.setOnClickListener { viewModel.restart() }
        binding.btnMain.setOnClickListener { findNavController().popBackStack() }
        binding.btnRanking.setOnClickListener { findNavController().navigate(R.id.game_to_ranking) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.oppoRPS.observe(viewLifecycleOwner, Observer {
            when (it) {
                GameViewModel.RPS.SCISSORS -> binding.imgOppo.setImageResource(R.drawable.scissors)
                GameViewModel.RPS.ROCK -> binding.imgOppo.setImageResource(R.drawable.rock)
                GameViewModel.RPS.PAPER -> binding.imgOppo.setImageResource(R.drawable.paper)
                else -> binding.imgOppo.setImageDrawable(null)
            }
        })
        viewModel.myRPS.observe(viewLifecycleOwner, Observer {
            when (it) {
                GameViewModel.RPS.SCISSORS -> binding.imgMe.setImageResource(R.drawable.scissors)
                GameViewModel.RPS.ROCK -> binding.imgMe.setImageResource(R.drawable.rock)
                GameViewModel.RPS.PAPER -> binding.imgMe.setImageResource(R.drawable.paper)
                else -> binding.imgMe.setImageDrawable(null)
            }
        })
        viewModel.result.observe(viewLifecycleOwner, Observer {
            when (it) {
                GameViewModel.Result.FAIL -> {
                    lifecycleScope.launch {
                        delay(1000)
                        GameResultDialogFragment().show(childFragmentManager, "Result")
                        binding.resultBtns.visibility = View.VISIBLE
                    }
                }
                GameViewModel.Result.DRAW -> {
                    lifecycleScope.launch {
                        delay(2000)
                        viewModel.next()
                    }
                }
                GameViewModel.Result.SUCCESS -> {
                    lifecycleScope.launch {
                        delay(2000)
                        viewModel.next()
                    }
                }
                null -> {}
            }
            binding.bigText.text = it.name
        })
        viewModel.state.observe(viewLifecycleOwner, Observer {
            binding.btnRestart.visibility = View.GONE
            binding.bigText.visibility = View.GONE
            binding.imgMe.visibility = View.GONE
            binding.imgOppo.visibility = View.GONE
            binding.RPSBtns.visibility = View.GONE
            binding.resultBtns.visibility = View.GONE
            binding.progress.visibility = View.GONE

            when (it) {
                GameViewModel.State.READY -> {
                    binding.bigText.visibility = View.VISIBLE
                }
                GameViewModel.State.PROGRESS -> {
                    binding.imgOppo.visibility = View.VISIBLE
                    binding.progress.visibility = View.VISIBLE
                    binding.RPSBtns.visibility = View.VISIBLE
                }
                GameViewModel.State.RESULT -> {
                    binding.imgOppo.visibility = View.VISIBLE
                    binding.imgMe.visibility = View.VISIBLE
                    binding.bigText.visibility = View.VISIBLE
                }
                null -> {}
                else -> {}
            }
        })

        if (savedInstanceState == null) viewModel.next()
    }

}