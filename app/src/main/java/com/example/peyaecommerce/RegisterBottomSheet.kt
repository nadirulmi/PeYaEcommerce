package com.example.peyaecommerce

import android.content.Context
import android.content.Intent
import android.util.Patterns
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RegisterBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_register_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val emailField = view.findViewById<EditText>(R.id.emailField)
        val nameField = view.findViewById<EditText>(R.id.nameField)
        val passwordField = view.findViewById<EditText>(R.id.passwordField)
        val confirmPasswordField = view.findViewById<EditText>(R.id.confirmPasswordField)
        val registerButton = view.findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val name = nameField.text.toString().trim()
            val pass = passwordField.text.toString()
            val confirmPass = confirmPasswordField.text.toString()

            if (email.isEmpty() || name.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(context, "Completá todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailField.error = "Email inválido"
                return@setOnClickListener
            }

            if (pass != confirmPass) {
                confirmPasswordField.error = "Las contraseñas no coinciden"
                return@setOnClickListener
            }

            val sharedPref = requireContext().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE)
            sharedPref.edit().apply {
                putString("userEmail", email)
                putString("userName", name)
                apply()
            }

            Toast.makeText(context, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), FragmentHolderActivity::class.java)
            startActivity(intent)

             dismiss()
             activity?.finish()

        }
    }
}
