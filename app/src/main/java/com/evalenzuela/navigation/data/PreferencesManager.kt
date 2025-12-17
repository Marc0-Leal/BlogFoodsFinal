package com.evalenzuela.navigation.data

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("LocalStorage", Context.MODE_PRIVATE)

    fun saveUserData(email: String, password: String) {
        sharedPreferences.edit()
            .putString("user_email", email)
            .putString("user_password", password)
            .apply()
    }

    fun getUserEmail(): String? = sharedPreferences.getString("user_email", null)
    fun getUserPassword(): String? = sharedPreferences.getString("user_password", null)

    fun saveLoginState(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean("isLoggedIn", isLoggedIn).apply()
    }

    fun isLoggedIn(): Boolean = sharedPreferences.getBoolean("isLoggedIn", false)

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }
}
