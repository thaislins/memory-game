package com.example.memorygame.modules.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.memorygame.R
import com.example.memorygame.modules.home.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.fragment_menu.*
import org.koin.android.ext.android.inject

class MenuFragment : Fragment() {

    private val productViewModel: ProductViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.hide()

        fadeIn()
        productViewModel.loadProducts()
        goToGameFragment()
    }

    private fun fadeIn() {
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.interpolator = DecelerateInterpolator()
        fadeIn.duration = 1000

        val animation = AnimationSet(false)
        animation.addAnimation(fadeIn)
        view?.animation = animation
    }

    private fun goToGameFragment() {
        btnPlay.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelableArrayList("key_product_list", productViewModel.products.value)
            view?.findNavController()?.navigate(R.id.toGameFragment, bundle)
        }

        productViewModel.products.observe(this, Observer {
            if (it != null) {
                pbLoadProducts.visibility = View.GONE
                btnPlay.visibility = View.VISIBLE
            }
        })
    }
}
