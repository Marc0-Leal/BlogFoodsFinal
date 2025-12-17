package com.evalenzuela.navigation.data.repository

import com.evalenzuela.navigation.data.model.Receta
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecetaRepository {
    private val api = RetrofitClient.recetaApi

    suspend fun getAllRecetas(): List<Receta> = withContext(Dispatchers.IO) {
        try {
            val response = api.getAllRecetas()
            if (response.isSuccessful && response.body() != null) {
                response.body()!!
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun createReceta(receta: Receta): Receta? = withContext(Dispatchers.IO) {
        try {
            val response = api.createReceta(receta)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun deleteReceta(id: Int): Boolean = withContext(Dispatchers.IO) {
        try {
            val response = api.deleteReceta(id)
            response.isSuccessful
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}