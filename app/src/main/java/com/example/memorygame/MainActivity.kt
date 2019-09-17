package com.example.memory_game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.memory_game.modules.product.viewmodel.ProductViewModel
import com.example.memorygame.R

class MainActivity : AppCompatActivity() {

    val viewModel by lazy {
        ViewModelProviders.of(this).get(ProductViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.loadProducts()
    }
}
