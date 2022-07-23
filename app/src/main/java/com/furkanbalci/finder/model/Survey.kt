package com.furkanbalci.finder.model

data class Survey(

    val id: Int,
    val options: List<Option>,
    val category: Category,
    var selectedOption: Option? = null
)