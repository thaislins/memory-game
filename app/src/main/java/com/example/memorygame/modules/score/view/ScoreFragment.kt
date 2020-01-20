package com.example.memorygame.modules.score.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.memorygame.databinding.FragmentScoreBinding
import com.example.memorygame.modules.score.view.adapter.ScoreAdapter
import com.example.memorygame.modules.score.viewmodel.ScoreViewModel
import kotlinx.android.synthetic.main.fragment_options.*
import org.koin.android.ext.android.inject

class ScoreFragment : Fragment() {

    private val scoreViewModel: ScoreViewModel by inject()
    private lateinit var binding: FragmentScoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentScoreBinding.inflate(inflater, container, false)
        binding.viewModel = scoreViewModel
        binding.lifecycleOwner = this
        binding.rvScore.adapter = ScoreAdapter(emptyList())

        with(binding.rvScore) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pressBackButton()
        scoreViewModel.loadScoreList()
    }

    fun pressBackButton() {
        btnReturn.setOnClickListener {
            view?.findNavController()?.navigateUp()
        }
    }
}
