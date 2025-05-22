package com.example.peyaecommerce

import android.os.Bundle
import android.widget.Button
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FragmentHolderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_holder)

        val logoutButton = findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            // Borrar bandera de sesión en SharedPreferences
            val sharedPref = getSharedPreferences("loginPrefs", MODE_PRIVATE)
            sharedPref.edit().apply {
                putBoolean("isLoggedIn", false)
                apply()
            }

            // Volver a LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()  // Cerramos FragmentHolderActivity para que no se pueda volver con "atrás"
        }
    }
}
