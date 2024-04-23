package com.example.composetutorial.model.filter

data class SetFilter(
    override var filterType: FilterType,
    val set: String
) : Filter