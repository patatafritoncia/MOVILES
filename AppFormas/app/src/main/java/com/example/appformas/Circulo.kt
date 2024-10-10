package com.example.appformas

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Circulo : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_circulo)

        val bundle:Bundle ?= intent.extras
        val lado : Double ?= bundle!!.getDouble("ladoC")
        //Toast.makeText(this,mensaje2,Toast.LENGTH_LONG).show()

        //recoger el componente
        val area = bundle.getDouble("radioC") * Math.PI
        val perimetro = bundle.getDouble("alturaT") * bundle.getDouble("baseT")
        val resulP = findViewById<TextView>(R.id.resulPeriCir)
        val resulA = findViewById<TextView>(R.id.resulAreaCir)
        resulP.text = perimetro.toString()
        resulA.text = area.toString()
    }
}