package com.evalenzuela.navigation.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evalenzuela.navigation.data.model.Favorite
import com.evalenzuela.navigation.data.repository.FavoriteRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repo: FavoriteRepository = FavoriteRepository()
) : ViewModel() {

    // Lista observable de nombres de recetas favoritas (solo para UI)
    val favoriteRecipes = mutableStateListOf<String>()

    // Lista completa de favoritos con IDs (para poder eliminar)
    private val _favoritesWithIds = mutableMapOf<String, Int>()

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            try {
                val favorites = repo.getFavorites()
                favoriteRecipes.clear()
                _favoritesWithIds.clear()

                favorites.forEach { fav ->
                    favoriteRecipes.add(fav.title)
                    fav.id?.let { id ->
                        _favoritesWithIds[fav.title] = id
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // Para agregar desde HomeScreen (con descripción)
    fun addFavoriteToApi(title: String, descripcion: String) {
        viewModelScope.launch {
            if (!favoriteRecipes.contains(title)) {
                try {
                    val favorite = Favorite(
                        id = null,
                        title = title,
                        descripcion = descripcion
                    )

                    val result = repo.addFavorite(favorite)
                    if (result != null) {
                        favoriteRecipes.add(title)
                        result.id?.let { id ->
                            _favoritesWithIds[title] = id
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    // Función legacy (mantener compatibilidad con FavoriteScreen)
    fun addFavorite(recipeName: String) {
        addFavoriteToApi(recipeName, "Receta favorita")
    }

    fun removeFavorite(recipeName: String) {
        viewModelScope.launch {
            try {
                val favoriteId = _favoritesWithIds[recipeName]
                if (favoriteId != null) {
                    val success = repo.deleteFavorite(favoriteId)
                    if (success) {
                        favoriteRecipes.remove(recipeName)
                        _favoritesWithIds.remove(recipeName)
                    }
                } else {
                    // Si no tiene ID, solo lo removemos de la lista local
                    favoriteRecipes.remove(recipeName)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // En caso de error, igual lo removemos localmente
                favoriteRecipes.remove(recipeName)
            }
        }
    }
}