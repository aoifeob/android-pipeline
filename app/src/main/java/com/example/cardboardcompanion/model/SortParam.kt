package com.example.cardboardcompanion.model

enum class SortParam(display: String) {
    NAME_ASC("Name (Ascending)"),
    NAME_DESC("Name (Descending)"),
    PRICE_ASC ("Price (Ascending)"),
    PRICE_DESC ("Price (Descending)"),
    SET_ASC ("Set (Ascending)"),
    SET_DESC ("Set (Descending)");

    var displayName = display

}