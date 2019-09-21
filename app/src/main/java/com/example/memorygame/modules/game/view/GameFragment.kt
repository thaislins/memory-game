package com.example.memorygame.modules.game.view


import android.os.Bundle
import android.os.Handler
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
import kotlinx.android.synthetic.main.fragment_game.view.*
import kotlinx.android.synthetic.main.item_card.view.*
import java.util.*

class GameFragment : Fragment() {

    private var cards = mutableListOf<Card>()
    private var posFirstCardFaceUp: Int = -1
    private var posSecondCardFaceUp: Int = -1
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
        val products: ArrayList<Product>? = arguments?.getParcelableArrayList("key_product_list")
        binding.viewModel?.createCards(8, 2, products)
        binding.viewModel?.createCards(10, 2, products)
        setClickListener()
    }

    private fun setClickListener() {
        binding.gridView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            // Get the GridView selected/clicked item text
            val adapter = binding.gridView.adapter as CardAdapter

            if (posSecondCardFaceUp == -1) chooseCard(position, adapter)
        })
    }

    private fun chooseCard(position: Int, adapter: CardAdapter) {
        if (!cards[position].isMatched && posFirstCardFaceUp != -1) {
            posSecondCardFaceUp = position
            cards[posSecondCardFaceUp].isFaceUp = true
            adapter.notifyDataSetChanged()

            Handler().postDelayed({
                checkIfCardsMatch(adapter)
            }, 900)
        } else {
            posFirstCardFaceUp = position
            cards[posFirstCardFaceUp].isFaceUp = true
            adapter.notifyDataSetChanged()
        }
    }

    private fun checkIfCardsMatch(adapter: CardAdapter) {
        if (cards[posFirstCardFaceUp].id == cards[posSecondCardFaceUp].id) {
            cards[posFirstCardFaceUp].isMatched = true
            cards[posSecondCardFaceUp].isMatched = true
        }

        cards[posFirstCardFaceUp].isFaceUp = false
        cards[posSecondCardFaceUp].isFaceUp = false

        posFirstCardFaceUp = -1
        posSecondCardFaceUp = -1
        adapter.notifyDataSetChanged()
    }
}
