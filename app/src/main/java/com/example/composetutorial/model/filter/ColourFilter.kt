package com.example.composetutorial.model.filter

import com.example.composetutorial.model.card.CardColour

data class ColourFilter(
    override var filterType: FilterType,
    val colours: List<CardColour>
) : Filter