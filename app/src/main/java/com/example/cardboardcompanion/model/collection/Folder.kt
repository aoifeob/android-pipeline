package com.example.cardboardcompanion.model.collection

import com.example.cardboardcompanion.model.card.CardCollection

data class Folder (

    var id: Long,

    var name: String,

    var cardCount: Long,

    var totalPrice: Double,

    var contents: CardCollection

)

