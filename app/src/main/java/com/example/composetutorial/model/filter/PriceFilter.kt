package com.example.composetutorial.model.filter

data class PriceFilter(
    override var filterType: FilterType,
    val minPrice: Double,
    val maxPrice: Double
) : Filter