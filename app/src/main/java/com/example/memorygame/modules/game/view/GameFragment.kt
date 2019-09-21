package com.example.memorygame.modules.game.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.memory_game.modules.card.model.Card
import com.example.memory_game.modules.product.model.Product
import com.example.memorygame.R
import com.example.memorygame.adapter.CardAdapter
import com.example.memorygame.databinding.FragmentGameBinding
import com.example.memorygame.modules.game.viewmodel.GameViewModel
import java.util.*

class GameFragment : Fragment() {

    var cards = mutableListOf<Card>()
    var posOnlyFaceUpCard: Int = -1
    private lateinit var binding: FragmentGameBinding
    val gameViewModel: GameViewModel? by lazy {
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
        binding.viewModel?.createCards(2, 2, products)
        setClickListener()
    }

    private fun setClickListener() {
        binding.gridView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            // Get the GridView selected/clicked item text
            val adapter = binding.gridView.adapter as CardAdapter

            chooseCard(position)
            adapter.notifyDataSetChanged()
        })
    }


    private fun chooseCard(position: Int) {
        if (!cards[position].isMatched && posOnlyFaceUpCard != -1) {
            // checks if cards match
            val matchIndex = posOnlyFaceUpCard
            if (matchIndex != position) {
                if (cards[position].id == cards[matchIndex].id) {
                    cards[position].isMatched = true
                    cards[matchIndex].isMatched = true
                }
                cards[position].isFaceUp = true
                posOnlyFaceUpCard = -1
            }
        } else {
            // no cards or 2 cards are facing up
            for (i in 0 until cards.size) {
                cards[i].isFaceUp = false
            }
            cards[position].isFaceUp = true
            posOnlyFaceUpCard = position
        }
    }
}
