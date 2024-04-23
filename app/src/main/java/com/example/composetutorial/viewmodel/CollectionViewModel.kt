package com.example.composetutorial.viewmodel

import com.example.composetutorial.model.SortParam
import com.example.composetutorial.model.card.Card
import com.example.composetutorial.model.filter.ColourFilter
import com.example.composetutorial.model.filter.Filter
import com.example.composetutorial.model.filter.PriceFilter
import com.example.composetutorial.model.filter.SetFilter

class CollectionViewModel {

    private var collection: List<Card> = listOf();

    fun displayCollection(
        searchString: String,
        sortParam: SortParam,
        filter: Filter?
    ): List<Card> {
        if (searchString.isNotEmpty()) {
            collection.filter {
                it.name.contains(searchString)
            }
        }

        if (filter != null) {
            filterCollection(filter)
        }

        sortCollection(sortParam)

        return collection;
    }

    private fun filterCollection(filter: Filter) {
        when (filter) {
            is PriceFilter -> collection.filter {
                filter.minPrice >= it.price && filter.maxPrice <= it.price
            }

            is SetFilter -> collection.filter {
                filter.set == it.set
            }

            is ColourFilter -> collection.filter {
                filter.colours.size == it.colours.size && filter.colours.containsAll(it.colours)
            }
        }
    }

    private fun sortCollection(sortParam: SortParam) {
        when (sortParam){
            SortParam.NAME_ASC -> collection.sortedBy { it.name }

            SortParam.NAME_DESC -> collection.sortedByDescending { it.name }

            SortParam.SET_ASC -> collection.sortedBy { it.set }

            SortParam.SET_DESC -> collection.sortedByDescending { it.set }

            SortParam.PRICE_ASC -> collection.sortedBy { it.price }

            SortParam.PRICE_DESC -> collection.sortedByDescending { it.price }

        }
    }

}