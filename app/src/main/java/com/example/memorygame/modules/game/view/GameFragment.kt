package com.example.memorygame.modules.game.view

import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
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
        observeMatchedCardCount()
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
            Game.amountOfMoves = it
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

    /**
     * Ends game in case all cards are matched
     *
     */
    private fun observeMatchedCardCount() {
        gameViewModel?.matchedCardCount?.observe(this, Observer {
            tvScore.text = (it / 2).toString()
            if (it != 0 && it == cards.size) {
                chronometer.stop()
                showEndGameDialog(it / 2)
            }
        })
    }

    /**
     * Shows dialog at end of game that gives quit or restart option
     *
     */
    private fun showEndGameDialog(amountOfPairsMatched: Int) {
        val dialogBuilder = activity?.let { AlertDialog.Builder(it) }
        val dialogView = View.inflate(context, R.layout.end_game_dialog, null);
        dialogBuilder?.setView(dialogView)
        dialogBuilder?.setCancelable(false);

        dialogBuilder?.setNegativeButton("QUIT") { _, _ ->
            NavHostFragment.findNavController(this).navigate(R.id.backToMenuFragment)
        }

        dialogBuilder?.setPositiveButton("RESTART") { _, _ ->
            startGame()
        }

        val alertDialog = dialogBuilder?.create()
        alertDialog?.show()
        val starTwo = alertDialog?.findViewById<ImageView>(R.id.ivStarTwo)
        val starThree = alertDialog?.findViewById<ImageView>(R.id.ivStarThree)
        showStars(starTwo, starThree)
        val tvAmountPairsMatched: TextView? =
            alertDialog?.findViewById<TextView>(R.id.tvTotalAmountMatched)
        tvAmountPairsMatched?.text = amountOfPairsMatched.toString()
    }

    /**
     * Shows amount of starts player obtained in game
     *
     */
    private fun showStars(starTwo: ImageView?, starThree: ImageView?) {
        var starCounter = 0

        if (SystemClock.elapsedRealtime() - chronometer.getBase() <= 150000) {
            starCounter++
        }

        if (Game.amountOfMoves <= (Game.amountOfPairs * 6)) {
            starCounter++
        }

        if (starCounter == 1)  {
            starTwo?.setImageDrawable(ContextCompat.getDrawable(activity?.applicationContext!!, R.drawable.ic_star_red))
        } else if (starCounter == 2) {
            starTwo?.setImageDrawable(ContextCompat.getDrawable(activity?.applicationContext!!, R.drawable.ic_star_red))
            starThree?.setImageDrawable(ContextCompat.getDrawable(activity?.applicationContext!!, R.drawable.ic_star_red))
        }
    }

    private fun setClickListener() {
        binding.gridView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                // Get the GridView selected/clicked item text
                try {
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
