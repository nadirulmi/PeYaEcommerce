package com.example.peyaecommerce.view.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peyaecommerce.model.data.remote.ApiService
import com.example.peyaecommerce.model.data.remote.LoginRequest
import com.example.peyaecommerce.model.data.remote.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var emailError by mutableStateOf<String?>(null)
    var passwordError by mutableStateOf<String?>(null)
    var isButtonEnabled by mutableStateOf(false)
    var loginMessage by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)

    fun clearLoginMessage() {
        loginMessage = null
    }

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
        if (!isButtonEnabled) {
            loginMessage = "Completa los campos correctamente"
            return
        }

        viewModelScope.launch {
            try {
                isLoading = true
                loginMessage = null
                val request = LoginRequest(email = email, encryptedPassword = password)
                val response = apiService.loginUser(request)

                if (response.isSuccessful) {
                    val user = response.body()?.user
                    if (user != null) {
                        userPreferences.saveUser(user)
                    }
                    loginMessage = "Login exitoso"
                } else {
                    val errorBodyString = response.errorBody()?.string()
                    val errorMessage = try {
                        val jsonObj = JSONObject(errorBodyString ?: "")
                        jsonObj.getString("message")
                    } catch (e: Exception) {
                        errorBodyString ?: "Error desconocido"
                    }
                    loginMessage = "Error: $errorMessage"
                }
            } catch (e: Exception) {
                loginMessage = "Error: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}
