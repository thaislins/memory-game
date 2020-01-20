package com.example.memorygame.modules.score.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.memorygame.R
import com.example.memorygame.modules.score.viewmodel.ScoreViewModel
import kotlinx.android.synthetic.main.fragment_options.*
import org.koin.android.ext.android.inject

class ScoreFragment : Fragment() {

    private val scoreViewModel: ScoreViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score, container, false)
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
