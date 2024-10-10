package com.example.appbotones

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnApagado = findViewById<ToggleButton>(R.id.tggBtnApagado)
        val txtApagado = findViewById<TextView>(R.id.txtEstado)

        val switchConexion = findViewById<Switch>(R.id.sConexion)
        val txtConexion = findViewById<TextView>(R.id.txtConexion)

        val check = findViewById<Button>(R.id.check)
        val txtCheck = findViewById<TextView>(R.id.txtCheck)

        btnApagado.setOnClickListener()
    }
}