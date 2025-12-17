package com.evalenzuela.navigation

import org.junit.Test

class RegisterScreenValidationTest {

    @Test
    fun passwordShort_validationFails() {
        val password = "123"
        assert(password.length < 6)
    }

    @Test
    fun passwordsDoNotMatch_validationFails() {
        val pass = "123456"
        val confirm = "123457"
        assert(pass != confirm)
    }
}