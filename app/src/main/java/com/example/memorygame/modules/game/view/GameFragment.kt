package com.example.memorygame.modules.game.view

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.memorygame.modules.game.model.Card
import com.example.memorygame.modules.home.model.Product
import com.example.memorygame.R
import com.example.memorygame.adapter.CardAdapter
import com.example.memorygame.databinding.FragmentGameBinding
import com.example.memorygame.exceptions.CardAlreadySelectedException
import com.example.memorygame.modules.game.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.fragment_game.*
import java.util.*

class GameFragment : Fragment() {

    private var cards = mutableListOf<Card>()
    private lateinit var binding: FragmentGameBinding
    private var gameOver = 0
    private val gameViewModel: GameViewModel? by lazy {
        ViewModelProviders.of(this).get(GameViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        binding.viewModel = gameViewModel
        val adapter = CardAdapter(activity?.applicationContext!!, R.layout.item_card, cards)
        binding.gridView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val products: ArrayList<Product>? = arguments?.getParcelableArrayList("key_product_list")
        binding.viewModel?.createCards(10, 2, products)
        setClickListener()
        Handler().postDelayed({ hideCards() }, 1000)
        addCountdownTimer()
    }

    private fun addCountdownTimer() {
        object : CountDownTimer(90000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                val min = (millisUntilFinished / 1000) / 60
                val sec = (millisUntilFinished / 1000).rem(60)
                val secString : String = if (sec < 10) "0$sec" else sec.toString()

                tvCountdown.text = resources.getString(R.string.timer_value, min, secString)
            }

            override fun onFinish() {
                gameOver = 1
                showEndGameDialog()
            }
        }.start()
    }

    fun showEndGameDialog() {
        val dialogBuilder = activity?.let { AlertDialog.Builder(it) }
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.end_game_dialog, null)
        dialogBuilder?.setView(dialogView)

        val alertDialog = dialogBuilder?.create()
        alertDialog?.show()
    }

    private fun hideCards() {
        val adapter = binding.gridView.adapter as CardAdapter
        cards.forEach { it.isFaceUp = false }
        adapter.changedPositions = (0..cards.size).toSet()
        adapter.notifyDataSetChanged()
    }

    private fun setClickListener() {
        binding.gridView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                // Get the GridView selected/clicked item text
                try {
                    binding.viewModel?.chooseCard(position)
                    val adapter = binding.gridView.adapter as CardAdapter
                    adapter.changedPositions = setOf(position)

                    gameViewModel?.updateLayout?.observe(this, Observer {
                        if (it) adapter.notifyDataSetChanged()
                    })
                } catch (e: CardAlreadySelectedException) {
                    // Do not do anything when re-selecting an already selected card
                }
            }
    }
}
