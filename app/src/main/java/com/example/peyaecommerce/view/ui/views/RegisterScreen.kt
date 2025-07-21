package com.example.peyaecommerce.view.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.peyaecommerce.R
import com.example.peyaecommerce.view.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current

    val showDialog = remember { mutableStateOf(false) }
    val registrationMessage = viewModel.registrationMessage

    LaunchedEffect(registrationMessage) {
        showDialog.value = registrationMessage != null
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.food4),
            contentDescription = "Fondo de comida",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier.align(Alignment.Center)
                .padding(24.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Crear cuenta",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 28.sp,
                        color = Color(0xFF7B2641),
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Nombre
                OutlinedTextField(
                    value = viewModel.name,
                    onValueChange = viewModel::onNameChange,
                    placeholder = { Text("Nombre") },
                    isError = viewModel.nameError != null,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(12.dp)
                )
                viewModel.nameError?.let { Text(it, color = Color.Red) }

                Spacer(Modifier.height(12.dp))

                // Apellido
                OutlinedTextField(
                    value = viewModel.lastName,
                    onValueChange = viewModel::onLastNameChange,
                    placeholder = { Text("Apellido") },
                    isError = viewModel.lastNameError != null,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(12.dp)
                )
                viewModel.lastNameError?.let { Text(it, color = Color.Red) }

                Spacer(Modifier.height(12.dp))

                // Email
                OutlinedTextField(
                    value = viewModel.email,
                    onValueChange = viewModel::onEmailChange,
                    placeholder = { Text("Email") },
                    isError = viewModel.emailError != null,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
                )
                viewModel.emailError?.let { Text(it, color = Color.Red) }

                Spacer(Modifier.height(12.dp))

                // Nacionalidad
                OutlinedTextField(
                    value = viewModel.nationality,
                    onValueChange = viewModel::onNationalityChange,
                    placeholder = { Text("Nacionalidad") },
                    isError = viewModel.nationalityError != null,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(12.dp)
                )
                viewModel.nationalityError?.let { Text(it, color = Color.Red) }

                Spacer(Modifier.height(12.dp))

                // Contraseña
                OutlinedTextField(
                    value = viewModel.password,
                    onValueChange = viewModel::onPasswordChange,
                    placeholder = { Text("Contraseña") },
                    isError = viewModel.passwordError != null,
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(12.dp)
                )
                viewModel.passwordError?.let { Text(it, color = Color.Red) }

                Spacer(Modifier.height(12.dp))

                // Confirmar contraseña
                OutlinedTextField(
                    value = viewModel.confirmPassword,
                    onValueChange = viewModel::onConfirmPasswordChange,
                    placeholder = { Text("Confirmar contraseña") },
                    isError = viewModel.confirmPasswordError != null,
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(12.dp)
                )
                viewModel.confirmPasswordError?.let { Text(it, color = Color.Red) }

                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = {
                        focusManager.clearFocus()
                        viewModel.doRegister()
                    },
                    enabled = viewModel.isButtonEnabled && !viewModel.isLoading,
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (viewModel.isButtonEnabled) Color(0xFF800020) else Color.Gray
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    if (viewModel.isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(20.dp)
                        )
                    } else {
                        Text("Registrarse", color = Color.White)
                    }
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "¿Ya tenés cuenta? Inicia sesión",
                    color = Color(0xFF800020),
                    modifier = Modifier.clickable {
                        navController.navigate("login") {
                            popUpTo("register") { inclusive = true }
                        }
                    }
                )
            }
        }
        if (showDialog.value) {
            val isSuccess = registrationMessage?.contains("exitoso", ignoreCase = true) == true

            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val icon: ImageVector
                        val iconTint: Color
                        val titleText: String

                        if (isSuccess) {
                            icon = Icons.Default.CheckCircle
                            iconTint = Color(0xFF2E7D32)
                            titleText = "¡Éxito!"
                        } else {
                            icon = Icons.Default.Error
                            iconTint = Color(0xFFD32F2F)
                            titleText = "Error"
                        }

                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = iconTint,
                            modifier = Modifier.size(28.dp)
                        )

                        Spacer(Modifier.width(8.dp))

                        Text(
                            text = titleText,
                            style = MaterialTheme.typography.titleLarge.copy(
                                color = iconTint,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                    }
                },
                text = {
                    Text(
                        text = registrationMessage ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialog.value = false
                            viewModel.clearRegistrationMessage()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "OK",
                            style = MaterialTheme.typography.titleMedium,
                            color = if (isSuccess) Color(0xFF2E7D32) else Color(0xFFD32F2F),
                            modifier = Modifier.align(Alignment.CenterVertically),
                            textAlign = TextAlign.Center
                        )
                    }
                },
                shape = RoundedCornerShape(16.dp),
                containerColor = MaterialTheme.colorScheme.surface,
                modifier = Modifier.padding(16.dp)
            )
        }
    }

}

