package com.example.memory_game.modules.product.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Image {
    var id: String? = null
    var product_id: String? = null
    var src: String? = null
}