package com.example.peyaecommerce

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences("loginPrefs", MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            val intent = Intent(this, FragmentHolderActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        setContentView(R.layout.activity_login)

        val emailData = findViewById<EditText>(R.id.emailEditText)
        val passData = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val registerText = findViewById<TextView>(R.id.registerTextView)

        // Inicialmente deshabilitamos el botón
        loginButton.isEnabled = false

        // Validar campos y habilitar botón
        fun validateInputs() {
            val email = emailData.text.toString().trim()
            val password = passData.text.toString().trim()
            val enabled = email.isNotEmpty() && password.isNotEmpty()
            loginButton.isEnabled = enabled

            Log.d("BotonLogin", "Está habilitado? $enabled")
        }

        // Detectar cambios en los campos
        emailData.addTextChangedListener {validateInputs()  }

        passData.addTextChangedListener {
            validateInputs()
        }

        registerText.setOnClickListener {
            val bottomSheet = RegisterBottomSheet()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }

        loginButton.setOnClickListener {
            // Solo se hace login si el botón está habilitado (campos completos)
            sharedPref.edit().apply {
                putBoolean("isLoggedIn", true)
                apply()
            }

            Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, FragmentHolderActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}


