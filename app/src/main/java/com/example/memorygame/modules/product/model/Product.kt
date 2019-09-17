package com.example.memory_game.modules.product.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Product {
        var id: Any? = null
        var title: Any? = null
        var image: Image? = null
}