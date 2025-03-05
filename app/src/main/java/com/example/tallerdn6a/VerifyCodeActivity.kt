package com.example.tallerdn6a

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VerifyCodeActivity : AppCompatActivity() {

    private lateinit var etRecoverCode1: EditText
    private lateinit var etRecoverCode2: EditText
    private lateinit var etRecoverCode3: EditText
    private lateinit var etRecoverCode4: EditText
    private lateinit var btnVerifyCode: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_code)

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)

        etRecoverCode1 = findViewById(R.id.et_recoverCode)
        etRecoverCode2 = findViewById(R.id.et_recoverCode2)
        etRecoverCode3 = findViewById(R.id.et_recoverCode3)
        etRecoverCode4 = findViewById(R.id.et_recoverCode4)
        btnVerifyCode = findViewById(R.id.bt_verifyCode)


        btnVerifyCode.setOnClickListener {
            verificarCodigo()
        }
    }

    private fun verificarCodigo() {
        val inputCode = "${etRecoverCode1.text}${etRecoverCode2.text}${etRecoverCode3.text}${etRecoverCode4.text}"

        val savedCode = sharedPreferences.getString("Código Recuperación", "")

        if (inputCode == savedCode) {
            Toast.makeText(this, "Código correcto. Acceso permitido.", Toast.LENGTH_LONG).show()

            sharedPreferences.edit().remove("Código Recuperación").apply()

            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()

        } else {
            Toast.makeText(this, "Código incorrecto. Intenta de nuevo.", Toast.LENGTH_LONG).show()
        }
    }
}