package com.example.composetutorial.model

data class Folder (

    var id: Long,

    var name: String,

    var cardCount: Long,

    var totalPrice: Double,

    var contents: Map<Card, Int>

)

