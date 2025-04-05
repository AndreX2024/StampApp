package com.example.tallerdn6a.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tallerdn6a.R

class HomeActivity : AppCompatActivity() {

    private lateinit var button_Start : Button
    private lateinit var tv_registrationHome : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        button_Start = findViewById(R.id.bt_start)

        button_Start.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        tv_registrationHome = findViewById(R.id.tv_login_home)

        tv_registrationHome.setOnClickListener {
            val intent = Intent(this,RegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}