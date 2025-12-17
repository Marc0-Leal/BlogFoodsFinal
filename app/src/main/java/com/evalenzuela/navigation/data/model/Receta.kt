package com.evalenzuela.navigation.data.model

import com.google.gson.annotations.SerializedName

data class Receta(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("imagenUrl")
    val imagenUrl: String? = null,

    @SerializedName("descripcion")
    val descripcion: String
)

// Convertir Receta (API) a Item (UI)
fun Receta.toItem(): Item {
    val parts = descripcion.split("Preparación:", "PREPARACION:", limit = 2, ignoreCase = true)
    val ingredients = parts.getOrNull(0)?.trim() ?: descripcion
    val preparation = parts.getOrNull(1)?.trim() ?: ""

    return Item(
        id = id ?: 0,
        title = nombre,
        description = ingredients,
        preparacion = preparation,
        imageUri = imagenUrl
    )
}