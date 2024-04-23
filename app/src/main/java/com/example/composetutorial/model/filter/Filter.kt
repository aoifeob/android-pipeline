package com.example.composetutorial.model.filter

interface Filter {

    var filterType: FilterType;

    fun getFilterType() : FilterType {
        return filterType
    }

}