package com.example.appformas

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Triangulo : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_triangulo)

        val bundle:Bundle ?= intent.extras
        val lado : Double ?= bundle!!.getDouble("ladoC")
        //Toast.makeText(this,mensaje2,Toast.LENGTH_LONG).show()

        //recoger el componente
        val area = bundle.getDouble("alturaT") * bundle.getDouble("baseT")
        val perimetro = bundle.getDouble("alturaT") * bundle.getDouble("baseT")
        val resulP = findViewById<TextView>(R.id.resulPeriT)
        val resulA = findViewById<TextView>(R.id.resulAreaT)
        resulP.text = perimetro.toString()
        resulA.text = area.toString()
    }
}