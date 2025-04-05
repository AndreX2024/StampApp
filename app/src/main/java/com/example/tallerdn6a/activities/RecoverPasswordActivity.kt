package com.example.tallerdn6a.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tallerdn6a.R
import androidx.core.content.edit

class RecoverPasswordActivity : AppCompatActivity() {

    private lateinit var editText_recoverEmail : EditText
    private lateinit var bt_recoverCode : Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover_password)

        editText_recoverEmail = findViewById(R.id.et_email_recover)
        bt_recoverCode = findViewById(R.id.bt_recover_code)

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        bt_recoverCode.setOnClickListener {
            if (verificarCorreo()) {
                val intent = Intent(this,VerifyCodeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun verificarCorreo(): Boolean {
        val emailIngresado = editText_recoverEmail.text.toString().trim()
        val emailRegistrado = sharedPreferences.getString("Correo Eléctronico", "")

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()

        if (emailIngresado.isEmpty()) {
            Toast.makeText(this, "El campo no puede estar vacío.", Toast.LENGTH_SHORT).show()
            return false
        } else if (emailIngresado != emailRegistrado) {
            Toast.makeText(this, "El correo ingresado no esta registrado.", Toast.LENGTH_SHORT).show()
            return false
        } else if (!emailIngresado.matches(emailPattern)) {
            Toast.makeText(this, "Correo electrónico no válido", Toast.LENGTH_SHORT).show()
            return false
        } else {
            Toast.makeText(this, "El código de recuperación ha sido enviado exitosamente!", Toast.LENGTH_SHORT).show()


            val verificationCode = (1000..9999).random()

            sharedPreferences.edit() {
                putString("Código Recuperación", verificationCode.toString().trim())
            }

            Log.d("RecoverPasswordActivity", "verificarCorreo : Código de recuperación: $verificationCode")

        }

        return true
    }
}