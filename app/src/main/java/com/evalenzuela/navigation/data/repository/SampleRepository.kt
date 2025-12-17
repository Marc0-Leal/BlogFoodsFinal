package com.evalenzuela.navigation.data.repository

import androidx.compose.runtime.mutableStateListOf
import com.evalenzuela.navigation.R
import com.evalenzuela.navigation.data.model.Item

class SampleRepository {

    private val items = mutableStateListOf(
        Item(
            id = 1,
            title = "Sopaipillas",
            description =
                        "- 2 tazas de harina\n" +
                        "- 1 taza de zapallo cocido\n" +
                        "- 1 cda de manteca\n" +
                        "- Sal a gusto",
            preparacion =
                "1. Mezclar el zapallo con la harina y la manteca.\n" +
                        "2. Amasar, estirar y cortar.\n" +
                        "3. Freír hasta dorar.",
            imageUri = "android.resource://com.evalenzuela.navigation/${R.drawable.sopaipillas}"
        ),

        Item(
            id = 2,
            title = "Empanadas de queso",
            description =
                        "- 2 tazas de harina\n" +
                        "- 100 g de manteca\n" +
                        "- Queso para rellenar",
            preparacion =
                "1. Preparar la masa.\n" +
                        "2. Rellenar con queso.\n" +
                        "3. Freír o hornear hasta dorar.",
            imageUri = "android.resource://com.evalenzuela.navigation/${R.drawable.empanadas_quartirolo_ig01}"
        ),

        Item(
            id = 3,
            title = "Pastel de choclo",
            description =
                        "- 6 choclos molidos\n" +
                        "- 500 g de carne molida\n" +
                        "- 2 cebollas\n" +
                        "- 3 huevos duros\n" +
                        "- Aceitunas",
            preparacion =
                "1. Cocinar la carne con cebolla.\n" +
                        "2. Poner en fuente con el pino y cubrir con choclo molido.\n" +
                        "3. Hornear hasta dorar.",
            imageUri = "android.resource://com.evalenzuela.navigation/${R.drawable.pastel_de_choclo_chileno_1}"
        )
    )

    fun getAll(): List<Item> = items

    fun getById(id: Int): Item? {
        return items.find { it.id == id }
    }

    fun addItem(item: Item) {
        items.add(item)
    }

}
