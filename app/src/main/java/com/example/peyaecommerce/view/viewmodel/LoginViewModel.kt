package com.example.peyaecommerce.view.viewmodel


import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var emailError by mutableStateOf<String?>(null)
    var passwordError by mutableStateOf<String?>(null)
    var isButtonEnabled by mutableStateOf(false)
    var loginMessage by mutableStateOf<String?>(null)

    private var hasEmailBeenTouched by mutableStateOf(false)
    private var hasPasswordBeenTouched by mutableStateOf(false)

    fun onEmailChange(newEmail: String) {
        email = newEmail
        if (!hasEmailBeenTouched) hasEmailBeenTouched = true
        validateInputs()
    }

    fun onPasswordChange(newPass: String) {
        password = newPass
        if (!hasPasswordBeenTouched) hasPasswordBeenTouched = true
        validateInputs()
    }

    private fun validateInputs() {
        // Mostrar errores solo si el usuario ya tocó el campo
        emailError = if (hasEmailBeenTouched) {
            when {
                email.isBlank() -> "El email es requerido"
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Email inválido"
                else -> null
            }
        } else null

        passwordError = if (hasPasswordBeenTouched) {
            when {
                password.isBlank() -> "La contraseña es requerida"
                password.length < 8 -> "Mínimo 8 caracteres"
                else -> null
            }
        } else null

        isButtonEnabled = emailError == null && passwordError == null &&
                hasEmailBeenTouched && hasPasswordBeenTouched
    }

    fun doLogin() {
        if (email == "test@test.com" && password == "12345678") {
            Log.d("LoginViewModel", "Login exitoso")
            loginMessage = "Login exitoso"
        } else {
            loginMessage = "Credenciales incorrectas"
        }
    }
}
