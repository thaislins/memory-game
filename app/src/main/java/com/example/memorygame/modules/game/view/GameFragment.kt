package com.example.memorygame.modules.game.view

import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.example.memorygame.R
import com.example.memorygame.adapter.CardAdapter
import com.example.memorygame.databinding.FragmentGameBinding
import com.example.memorygame.exceptions.CardAlreadySelectedException
import com.example.memorygame.modules.game.model.Card
import com.example.memorygame.modules.game.model.Game
import com.example.memorygame.modules.game.viewmodel.GameViewModel
import com.example.memorygame.modules.home.model.Product
import kotlinx.android.synthetic.main.fragment_game.*
import java.util.*

class GameFragment : Fragment() {

    private var cards = mutableListOf<Card>()
    private var products: ArrayList<Product>? = null
    private lateinit var binding: FragmentGameBinding
    private val gameViewModel: GameViewModel? by lazy {
        ViewModelProviders.of(this).get(GameViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        binding.viewModel = gameViewModel
        binding.lifecycleOwner = this
        val adapter = CardAdapter(activity?.applicationContext!!, R.layout.item_card, cards)
        binding.gridView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        products = arguments?.getParcelableArrayList(resources.getString(R.string.key_product_list))
        checkMatchedCardCount()
        startGame()
        setClickListener()
    }

    private fun startGame() {
        updateAmountOfMoves()
        binding.viewModel?.createCards(Game.amountOfPairs, products)
        gameViewModel?.cards?.observe(this, Observer {
            Handler().postDelayed({ startChronometer() }, 1200)
        })
    }

    private fun updateAmountOfMoves() {
        gameViewModel?.amountOfMoves?.observe(this, Observer {
            tvAmountMoves.text = resources.getString(R.string.amount_moves, it)
        })
    }

    private fun startChronometer() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start()
    }

    private fun updateGameLayout(adapter: CardAdapter) {
        gameViewModel?.updateLayout?.observe(this, Observer {
            if (it) adapter.notifyDataSetChanged()
        })
    }

    private fun checkMatchedCardCount() {
        gameViewModel?.matchedCardCount?.observe(this, Observer {
            tvScore.text = (it / 2).toString()
            if (it != 0 && it == cards.size) {
                Game.gameOver = true
                chronometer.stop()
                showEndGameDialog()
            }
        })
    }

    private fun showEndGameDialog() {
        val dialogBuilder = activity?.let { AlertDialog.Builder(it) }
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.end_game_dialog, null)
        dialogBuilder?.setView(dialogView)
        dialogBuilder?.setCancelable(false);

        dialogBuilder?.setNegativeButton("QUIT") { _, _ ->
            NavHostFragment.findNavController(this).navigate(R.id.backToMenuFragment)
        }

        dialogBuilder?.setPositiveButton("RESTART", { _, _ ->
            startGame()
        })

        val alertDialog = dialogBuilder?.create()
        alertDialog?.show()
    }

    private fun setClickListener() {
        binding.gridView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                // Get the GridView selected/clicked item text
                try {
                    Game.gameOver = false
                    binding.viewModel?.chooseCard(position)
                    val adapter = binding.gridView.adapter as CardAdapter
                    adapter.changedPositions = setOf(position)

                    updateGameLayout(adapter)
                } catch (e: CardAlreadySelectedException) {
                    // Do not do anything when re-selecting an already selected card
                }
            }
    }
}
