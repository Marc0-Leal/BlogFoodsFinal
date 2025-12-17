package com.example.proyectoblog.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextFieldModificado(value: String,
                        onChange: (String) -> Unit,
                        error: String? = null,
                        label: String){

    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        label = {
            Text(label)
        },
        isError = error != null,
        supportingText = {
            error?.let{Text(it , color= MaterialTheme.colorScheme.error)}
        },
        modifier = Modifier.fillMaxWidth()
    )
}