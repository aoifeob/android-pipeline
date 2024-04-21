package com.example.composetutorial.model.card

data class Card (

    var id: Long,

    var name: String,

    var set: String,

    var collectorNo: Int,

    var image: Int,

    //TODO: support EUR and USD prices
    var price: Double,

    var quantity: Int,

    var colours: List<CardColour>

) {

    fun getDisplayPrice() : String {
        return "€%.${2}f".format(price)
    }

    fun getDisplayName() : String {
        return "($set) $name"
    }

}