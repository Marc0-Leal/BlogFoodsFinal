package com.evalenzuela.navigation.data.repository

import com.evalenzuela.navigation.data.model.Favorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteRepository {
    private val api = RetrofitClient.favoriteApi

    suspend fun getFavorites(): List<Favorite> = withContext(Dispatchers.IO) {
        try {
            val response = api.getAllFavorites()
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

    suspend fun addFavorite(fav: Favorite): Favorite? = withContext(Dispatchers.IO) {
        try {
            val response = api.addFavorite(fav)
            if (response.isSuccessful) response.body() else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun deleteFavorite(id: Int): Boolean = withContext(Dispatchers.IO) {
        try {
            val response = api.deleteFavorite(id)
            response.isSuccessful
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}