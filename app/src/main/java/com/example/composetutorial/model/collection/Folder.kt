package com.example.composetutorial.model.collection

import com.example.composetutorial.model.card.Card

data class Folder (

    var id: Long,

    var name: String,

    var cardCount: Long,

    var totalPrice: Double,

    var contents: Map<Card, Int>

)

