package com.example.peyaecommerce.view.ui.views
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.peyaecommerce.view.viewmodel.LoginViewModel
import androidx.compose.foundation.clickable

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = viewModel()
) {
    val email = viewModel.email
    val password = viewModel.password
    val emailError = viewModel.emailError
    val passwordError = viewModel.passwordError
    val isButtonEnabled = viewModel.isButtonEnabled
    val loginMessage = viewModel.loginMessage
    Log.d("LoginViewModel", "mensaje: $loginMessage")

    LaunchedEffect(loginMessage) {
        if (loginMessage == "Login exitoso") {
            Log.d("LoginViewModel", "Mensaje correcto")

            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Login", fontSize = 32.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("Email") },
            singleLine = true,
            isError = emailError != null,
            modifier = Modifier.fillMaxWidth()
        )
        emailError?.let {
            Text(text = it, color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError != null,
            modifier = Modifier.fillMaxWidth()
        )
        passwordError?.let {
            Text(text = it, color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.doLogin() },
            enabled = isButtonEnabled,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B2641))
        ) {
            Text("Iniciar sesión")
        }

        loginMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = it, color = if (it == "Login exitoso") Color.Green else Color.Red)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "¿No tenés cuenta? Registrate",
            color = Color(0xFF800020),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                navController.navigate("register"){
                    popUpTo("login") { inclusive = true }
                }
            }
        )
    }
}
