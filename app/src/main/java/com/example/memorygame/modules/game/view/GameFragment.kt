package com.example.memorygame.modules.game.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private lateinit var binding: FragmentGameBinding
    val gameViewModel: GameViewModel? by lazy {
        ViewModelProviders.of(this).get(GameViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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
    }
}
