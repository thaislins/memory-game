package com.example.memorygame.modules.game.view

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.memory_game.modules.card.model.Card
import com.example.memory_game.modules.product.model.Product
import com.example.memorygame.R
import com.example.memorygame.adapter.CardAdapter
import com.example.memorygame.databinding.FragmentGameBinding
import com.example.memorygame.exceptions.CardAlreadySelectedException
import com.example.memorygame.modules.game.viewmodel.GameViewModel
import java.util.*

class GameFragment : Fragment() {

    private var cards = mutableListOf<Card>()
    private lateinit var binding: FragmentGameBinding
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
        (activity as AppCompatActivity).supportActionBar!!.show()
        val products: ArrayList<Product>? = arguments?.getParcelableArrayList("key_product_list")
        binding.viewModel?.createCards(10, 2, products)
        setClickListener()
        Handler().postDelayed({ hideCards() }, 1000)
    }

    private fun hideCards() {
        val adapter = binding.gridView.adapter as CardAdapter
        for (pos in 0 until cards.size) {
            cards[pos].isFaceUp = false
            adapter.cardClickedPosition = pos
            adapter.notifyDataSetChanged()
        }
    }

    private fun setClickListener() {
        binding.gridView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                // Get the GridView selected/clicked item text
                try {
                    binding.viewModel?.chooseCard(position)
                    val adapter = binding.gridView.adapter as CardAdapter
                    adapter.cardClickedPosition = position

                    gameViewModel?.updateLayout?.observe(this, Observer {
                        if (it) adapter.notifyDataSetChanged()
                    })
                } catch (e: CardAlreadySelectedException) {
                    // Do not do anything when re-selecting an already selected card
                }
            }
    }
}
