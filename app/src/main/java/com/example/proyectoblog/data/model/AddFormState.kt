package com.example.proyectoblog.data.model

data class AddFormState (
    //Variables
    val name: String = "",
    val description: String = "",

    //Errores
    val nameError: String? = null,
    val descriptionError: String? = null

)