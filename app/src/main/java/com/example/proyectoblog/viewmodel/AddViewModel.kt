package com.example.proyectoblog.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.proyectoblog.data.model.AddFormState

class AddViewModel : ViewModel() {
    var formState = mutableStateOf(AddFormState())

    fun onChangeName(newValue: String){
        formState.value = formState.value.copy(
            name = newValue,
            nameError = when{
                newValue.isBlank() -> "Nombre vacio"
                else -> null
            }
        )

    }
}