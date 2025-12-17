package com.evalenzuela.navigation.data.model

data class Item(
    val id: Int,
    val title: String,
    val description: String,
    val preparacion: String,
    val imageUri: String? = null
)