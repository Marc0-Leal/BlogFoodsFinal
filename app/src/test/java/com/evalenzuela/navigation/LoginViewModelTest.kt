package com.evalenzuela.navigation

import com.evalenzuela.navigation.viewmodel.AuthViewModel
import org.junit.Test

class LoginViewModelTest {
    @Test
    fun signIn_withInvalidEmail_returnsError() {
        val vm = AuthViewModel()
        vm.signIn("bademail", "123456")

        assert(vm.error.value != null)
    }
}
