package com.evalenzuela.navigation.data.model

import com.google.gson.annotations.SerializedName

data class Favorite(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("title")
    val title: String,

    @SerializedName("descripcion")
    val descripcion: String
)