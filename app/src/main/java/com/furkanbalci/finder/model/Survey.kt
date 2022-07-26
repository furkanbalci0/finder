package com.furkanbalci.finder.model

data class Survey(
    val id: String,
    val options: List<String>,
    val category: Category,
    var selectedOption: String? = null,
    var completed: Boolean? = false
)