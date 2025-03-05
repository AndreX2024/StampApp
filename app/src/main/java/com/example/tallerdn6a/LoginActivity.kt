package com.example.tallerdn6a

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextUsername : EditText
    private lateinit var editTextPassword : EditText
    private lateinit var tv_noHaveAccount : TextView
    private lateinit var tv_recoverPassword : TextView
    private lateinit var button_login : Button

    private lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextUsername = findViewById(R.id.et_username)
        editTextPassword = findViewById(R.id.et_password)
        tv_noHaveAccount = findViewById(R.id.tv_have_account2)
        tv_recoverPassword = findViewById(R.id.tv_recover_password)
        button_login = findViewById(R.id.bt_sign_up)

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        button_login.setOnClickListener {
            if (validarCamposLogin()) {
                Toast.makeText(this,"Ingreso Concedido. Bienvenido!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this,ProfileActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        tv_recoverPassword.setOnClickListener {
            val intent = Intent(this,RecoverPasswordActivity::class.java)
            startActivity(intent)
            finish()
        }

        tv_noHaveAccount.setOnClickListener {
            val intent = Intent(this,RegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validarCamposLogin(): Boolean {
        val email = editTextUsername.text.toString().trim()
        val emailRegistrado = sharedPreferences.getString("Correo Eléctronico", "")

        val password = editTextPassword.text.toString().trim()
        val passwordRegistrada = sharedPreferences.getString("Contraseña", "")

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
        val passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,}\$".toRegex()


        if (email.isEmpty()) {
            Toast.makeText(this, "El campo correo eléctronico es requerido", Toast.LENGTH_SHORT).show()
            return false
        } else if (!email.matches(emailPattern)) {
            Toast.makeText(this, "Correo electrónico no válido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "El campo contraseña es requerido", Toast.LENGTH_SHORT).show()
            return false
        } else if (!password.matches(passwordPattern)) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres, una mayúscula, una minúscula y un número", Toast.LENGTH_LONG).show()
            return false
        }

        if (email != emailRegistrado || password != passwordRegistrada) {
            Toast.makeText(this, "Datos no registrados o incorrectos", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}