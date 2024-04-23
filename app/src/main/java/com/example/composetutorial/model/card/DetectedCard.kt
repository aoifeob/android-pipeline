package com.example.composetutorial.model.card

data class DetectedCard(
    var name: String,
    var set : String,
    var collectorNo: Int,
    var price: Double
) {

    fun getDisplayDetails() : String {
        return "$name ($set $collectorNo)"
    }

    fun getDisplayPrice() : String {
        return "€$price"
    }
}