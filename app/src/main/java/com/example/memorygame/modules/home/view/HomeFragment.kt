package com.example.memorygame.modules.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.memorygame.R
import com.example.memorygame.modules.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_menu.*
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by inject()

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
        homeViewModel.loadProducts()
        goToGameFragment()
        createErrorDialog()
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
            bundle.putParcelableArrayList("key_product_list", homeViewModel.products.value)
            view?.findNavController()?.navigate(R.id.toGameFragment, bundle)
        }

        homeViewModel.products.observe(this, Observer {
            if (it != null) {
                pbLoadProducts.visibility = View.GONE
                btnPlay.visibility = View.VISIBLE
            }
        })
    }

    fun createErrorDialog() {
        homeViewModel.error.observe(this, Observer {
            if (it) {
                val dialogBuilder = activity?.let { AlertDialog.Builder(it) }
                val inflater = this.layoutInflater
                val dialogView = inflater.inflate(R.layout.error_dialog, null)
                dialogBuilder?.setView(dialogView)
                dialogBuilder?.setCancelable(false);

                dialogBuilder?.setPositiveButton("RETRY") { _, _ ->
                    homeViewModel.loadProducts()
                    goToGameFragment()
                }

                val alertDialog = dialogBuilder?.create()
                alertDialog?.show()
            }
        })
    }
}
