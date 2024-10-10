package com.example.appformas

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Cuadrado : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cuadrado)

        val bundle:Bundle ?= intent.extras
        val lado : Double ?= bundle!!.getDouble("ladoC")
        //Toast.makeText(this,mensaje2,Toast.LENGTH_LONG).show()

        //recoger el componente
        val area = bundle.getDouble("ladoC") * bundle.getDouble("ladoC")
        val perimetro = bundle.getDouble("ladoC") * bundle.getDouble("ladoC")
        val resulP = findViewById<TextView>(R.id.resulCuaPerimetro)
        val resulA = findViewById<TextView>(R.id.resultCuaArea)
        resulP.text = perimetro.toString()
        resulA.text = area.toString()

    }
}