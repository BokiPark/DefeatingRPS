package com.boki.park.defeatingrps.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.boki.park.defeatingrps.databinding.DialogGameResultBinding

class GameResultDialogFragment: DialogFragment() {

    private val viewModel by lazy { ViewModelProvider(requireParentFragment()).get(GameViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DialogGameResultBinding.inflate(inflater, container, false).apply {
        viewModel = this@GameResultDialogFragment.viewModel
        btnCancel.setOnClickListener { dismiss() }
        btnRegister.setOnClickListener {
            this@GameResultDialogFragment.viewModel.setRecord(nick.text.toString())
            dismiss()
        }
    }.root
}