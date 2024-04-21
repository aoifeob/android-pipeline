package com.example.composetutorial.model.card

import com.example.composetutorial.R

enum class CardColour(display: String, image: Int) {
    WHITE ("White", R.drawable.mtg_colour_white),
    BLUE ("Blue", R.drawable.mtg_colour_blue),
    BLACK ("Black", R.drawable.mtg_colour_black),
    RED ("Red", R.drawable.mtg_colour_red),
    GREEN ("Green", R.drawable.mtg_colour_green),
    COLOURLESS ("Colourless", R.drawable.mtg_colour_colourless)
}