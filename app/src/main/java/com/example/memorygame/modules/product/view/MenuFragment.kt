package com.example.memorygame.modules.product.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.memory_game.modules.product.viewmodel.ProductViewModel
import com.example.memorygame.R
import kotlinx.android.synthetic.main.fragment_menu.*
import org.koin.android.ext.android.inject

class MenuFragment : Fragment() {

    val productViewModel: ProductViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.hide()

        productViewModel.loadProducts()
        goToGameFragment()
    }


    fun goToGameFragment() {
        btPlay.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelableArrayList("key_product_list", productViewModel.products.value)
            view?.findNavController()?.navigate(R.id.toGameFragment, bundle)
        }
    }
}
