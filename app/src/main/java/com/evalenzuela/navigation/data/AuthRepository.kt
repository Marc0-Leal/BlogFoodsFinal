package com.evalenzuela.navigation.data

data class User(val email: String, val password: String)

class AuthRepository {
    private val users = mutableListOf<User>()

    fun register(email: String, password: String): Result<Unit> {
        if (users.any { it.email == email }) return Result.failure(Exception("Usuario ya existe"))
        users.add(User(email, password))
        return Result.success(Unit)
    }

    fun login(email: String, password: String): Result<Unit> {
        val found = users.find { it.email == email && it.password == password }
        return if (found != null) Result.success(Unit)
        else Result.failure(Exception("Credenciales inválidas"))
    }

    init {
        users.add(User("benja@duoc.cl", "123456"))
        users.add(User("marco@duoc.cl", "123456"))
        users.add(User("diego@duoc.cl", "123456"))
        users.add(User("eduardo@duoc.cl", "123456"))
    }
}

