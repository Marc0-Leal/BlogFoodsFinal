package com.evalenzuela.navigation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evalenzuela.navigation.data.model.Item
import com.evalenzuela.navigation.data.model.Receta
import com.evalenzuela.navigation.data.model.toItem
import com.evalenzuela.navigation.data.repository.RecetaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repo: RecetaRepository = RecetaRepository()
) : ViewModel() {

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadItems()
    }

    fun loadItems() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val recetas = repo.getAllRecetas()
                _items.value = recetas.map { it.toItem() }
            } catch (e: Exception) {
                e.printStackTrace()
                _items.value = emptyList()
            }
            _isLoading.value = false
        }
    }

    fun addItem(item: Item) {
        viewModelScope.launch {
            val receta = Receta(
                nombre = item.title,
                descripcion = "${item.description}\nPreparación:\n${item.preparacion}",
                imagenUrl = item.imageUri
            )
            repo.createReceta(receta)
            loadItems() // Recargar lista
        }
    }

    fun getItem(id: Int): Item? = _items.value.find { it.id == id }
}