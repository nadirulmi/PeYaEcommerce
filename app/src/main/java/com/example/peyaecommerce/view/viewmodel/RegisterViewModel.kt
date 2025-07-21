package com.example.peyaecommerce.view.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peyaecommerce.model.data.remote.ApiService
import com.example.peyaecommerce.model.data.remote.RegisterRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    var name by mutableStateOf("")
    var lastName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var nationality by mutableStateOf("")

    var nameError by mutableStateOf<String?>(null)
    var lastNameError by mutableStateOf<String?>(null)
    var emailError by mutableStateOf<String?>(null)
    var passwordError by mutableStateOf<String?>(null)
    var confirmPasswordError by mutableStateOf<String?>(null)
    var nationalityError by mutableStateOf<String?>(null)

    var isButtonEnabled by mutableStateOf(false)
    var registrationMessage by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)

    private var touchedFields = mutableSetOf<String>()

    fun clearRegistrationMessage() {
        registrationMessage = null
    }

    fun onNameChange(newName: String) {
        name = newName
        touchedFields.add("name")
        validateInputs()
    }

    fun onLastNameChange(newLastName: String) {
        lastName = newLastName
        touchedFields.add("lastName")
        validateInputs()
    }

    fun onEmailChange(newEmail: String) {
        email = newEmail
        touchedFields.add("email")
        validateInputs()
    }

    fun onPasswordChange(newPass: String) {
        password = newPass
        touchedFields.add("password")
        validateInputs()
    }

    fun onConfirmPasswordChange(newConfirmPass: String) {
        confirmPassword = newConfirmPass
        touchedFields.add("confirmPassword")
        validateInputs()
    }

    fun onNationalityChange(newNationality: String) {
        nationality = newNationality
        touchedFields.add("nationality")
        validateInputs()
    }

    private fun validateInputs() {
        nameError = if ("name" in touchedFields) {
            when {
                name.isBlank() -> "El nombre es requerido"
                else -> null
            }
        } else null

        lastNameError = if ("lastName" in touchedFields) {
            when {
                lastName.isBlank() -> "El apellido es requerido"
                else -> null
            }
        } else null

        emailError = if ("email" in touchedFields) {
            when {
                email.isBlank() -> "El email es requerido"
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Email inválido"
                else -> null
            }
        } else null

        passwordError = if ("password" in touchedFields) {
            when {
                password.isBlank() -> "La contraseña es requerida"
                password.length < 8 -> "Mínimo 8 caracteres"
                else -> null
            }
        } else null

        confirmPasswordError = if ("confirmPassword" in touchedFields) {
            when {
                confirmPassword.isBlank() -> "La confirmación es requerida"
                confirmPassword != password -> "Las contraseñas no coinciden"
                else -> null
            }
        } else null

        nationalityError = if ("nationality" in touchedFields) {
            when {
                nationality.isBlank() -> "La nacionalidad es requerida"
                else -> null
            }
        } else null

        isButtonEnabled = listOf(
            nameError, lastNameError, emailError, passwordError,
            confirmPasswordError, nationalityError
        ).all { it == null } &&
                name.isNotBlank() && lastName.isNotBlank() &&
                email.isNotBlank() && password.isNotBlank() &&
                confirmPassword.isNotBlank() && nationality.isNotBlank()
    }

    fun doRegister() {
        if (name.isNotBlank() && lastName.isNotBlank() &&
            email.isNotBlank() && password.isNotBlank() && nationality.isNotBlank()
        ) {
            viewModelScope.launch {
                try {
                    isLoading = true
                    val request = RegisterRequest(
                        fullName = "$name $lastName",
                        email = email,
                        encryptedPassword = password,
                        nationality = nationality
                    )
                    val response = apiService.registerUser(request)

                    if (response.isSuccessful) {
                        registrationMessage = "Registro exitoso: ${response.body()?.fullName}"
                    } else {
                        val errorBodyString = response.errorBody()?.string()
                        val errorMessage = try {
                            val jsonObj = JSONObject(errorBodyString ?: "")
                            jsonObj.getString("message")
                        } catch (e: Exception) {
                            errorBodyString ?: "Error desconocido"
                        }
                        registrationMessage = "Error: $errorMessage"
                    }
                } catch (e: Exception) {
                    registrationMessage = "Error: ${e.message}"
                } finally {
                    isLoading = false
                }
            }
        } else {
            registrationMessage = "Completa todos los campos"
        }
    }
}
