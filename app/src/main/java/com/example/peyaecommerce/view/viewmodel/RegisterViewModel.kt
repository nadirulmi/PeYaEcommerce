package com.example.peyaecommerce.view.viewmodel

import android.app.Application
import android.content.Context
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel(){
    var fullName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var emailError by mutableStateOf<String?>(null)
    var passwordError by mutableStateOf<String?>(null)
    var confirmPasswordError by mutableStateOf<String?>(null)
    var fullNameError by mutableStateOf<String?>(null)
    var isButtonEnabled by mutableStateOf(false)
    var registrationMessage by mutableStateOf<String?>(null)

    // Nuevas variables para saber si el usuario ya tocó los campos
    private var hasFullNameBeenTouched by mutableStateOf(false)
    private var hasEmailBeenTouched by mutableStateOf(false)
    private var hasPasswordBeenTouched by mutableStateOf(false)
    private var hasConfirmPasswordBeenTouched by mutableStateOf(false)

    fun onFullNameChange(newFullName: String) {
        fullName = newFullName
        if (!hasFullNameBeenTouched) hasFullNameBeenTouched = true
        validateInputs()
    }
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
    fun onConfirmPasswordChange(newConfirmPass: String) {
        confirmPassword = newConfirmPass
        if (!hasConfirmPasswordBeenTouched) hasConfirmPasswordBeenTouched = true
        validateInputs()
    }

    private fun validateInputs() {
        fullNameError = if (hasFullNameBeenTouched) {
            when {
                fullName.isBlank() -> "El nombre completo es requerido"
                else -> null
            }
        } else null

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

        confirmPasswordError = if (hasConfirmPasswordBeenTouched) {
            when {
                confirmPassword.isBlank() -> "La confirmación de contraseña es requerida"
                confirmPassword != password -> "Las contraseñas no coinciden"
                else -> null
            }
        } else null

        isButtonEnabled = fullNameError == null && emailError == null && passwordError == null &&
                          confirmPasswordError == null &&
                          hasFullNameBeenTouched && hasEmailBeenTouched &&
                          hasPasswordBeenTouched && hasConfirmPasswordBeenTouched
    }

    fun doRegister() {
        if (email.isNotBlank() && password.isNotBlank() && confirmPassword == password) {
            registrationMessage = "Registro exitoso"
        } else {
            registrationMessage = "Error en el registro. Verifica tus datos."
        }
    }
}