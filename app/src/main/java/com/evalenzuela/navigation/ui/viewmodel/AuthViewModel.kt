package com.evalenzuela.navigation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _user = MutableStateFlow<String?>(null)
    val user: StateFlow<String?> get() = _user

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    // Login simple local (sin Firebase)
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            delay(500) // Simular llamada de red

            if (email.isNotBlank() && password.length >= 6) {
                _user.value = email
            } else {
                _error.value = "Credenciales inválidas"
            }

            _isLoading.value = false
        }
    }

    // Registro simple local (sin Firebase)
    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            delay(500) // Simular llamada de red

            if (email.contains("@") && password.length >= 6) {
                _user.value = email
            } else {
                _error.value = "Email o contraseña inválidos"
            }

            _isLoading.value = false
        }
    }

    fun signOut() {
        _user.value = null
    }

    fun setError(message: String?) {
        _error.value = message
    }
}