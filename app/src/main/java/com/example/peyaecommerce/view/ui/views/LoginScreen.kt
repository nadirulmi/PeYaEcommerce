package com.example.peyaecommerce.view.ui.views
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import com.example.peyaecommerce.R
import kotlinx.coroutines.delay

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
    var passwordVisible by remember { mutableStateOf(false) }


    LaunchedEffect(loginMessage) {
        if (loginMessage == "Login exitoso") {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo que ocupa toda la pantalla
        Image(
            painter = painterResource(id = R.drawable.food4),
            contentDescription = "Fondo de comida",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Card del formulario centrada
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .clip(RoundedCornerShape(32.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.White.copy(alpha = 0.95f), Color(0xFFA9818D))
                    )
                )
                .shadow(8.dp, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .padding(24.dp)
                .fillMaxWidth(0.9f),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                "Iniciar sesión",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 28.sp,
                    color = Color(0xFF7B2641),
                    fontWeight = FontWeight.Bold,
                )


                )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { viewModel.onEmailChange(it) },
                placeholder = { Text("Email") },
                singleLine = true,
                isError = emailError != null,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Icono Email",
                        tint = Color(0xFF7B2641)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFE8E0E0),
                    unfocusedContainerColor = Color(0xFFE8E0E0),
                    focusedBorderColor = Color(0xFF7B2641),
                    unfocusedBorderColor = Color.Gray,
                    errorBorderColor = Color(0xFFB00020)
                )
            )

            emailError?.let {
                Text(it, color = Color(0xFFB00020), fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { viewModel.onPasswordChange(it) },
                placeholder = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                isError = passwordError != null,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Icono contraseña",
                        tint = Color(0xFF7B2641)
                    )
                },
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    val description = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(image, contentDescription = description)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFE8E0E0),
                    unfocusedContainerColor = Color(0xFFE8E0E0),
                    focusedBorderColor = Color(0xFF7B2641),
                    unfocusedBorderColor = Color.Gray,
                    errorBorderColor = Color(0xFFB00020)
                )
            )

            passwordError?.let {
                Text(it, color = Color(0xFFB00020), fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.doLogin()
                },
                enabled = isButtonEnabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B2641))
            ) {
                Text("Iniciar sesión", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "¿No tenés cuenta? Registrate",
                color = Color(0xFFCBC9C9),
                modifier = Modifier.clickable {
                    navController.navigate("register") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
    }
}



