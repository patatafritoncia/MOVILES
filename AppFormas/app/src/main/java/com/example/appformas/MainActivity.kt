package com.example.appformas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val ladoCuadrado = findViewById<EditText>(R.id.ladoCuadrado)
        val num1Triangulo = findViewById<EditText>(R.id.num1Triangulo)
        val num2Triangulo = findViewById<EditText>(R.id.num2Triangulo)
        val radio = findViewById<EditText>(R.id.radioCirculo)

        val btnCuadrado = findViewById<Button>(R.id.btnCalCuadrado)
        val btnTriangulo = findViewById<Button>(R.id.btnCalTriangulo)
        val btnCirculo = findViewById<Button>(R.id.btnCalCirculo)

        btnCuadrado.setOnClickListener{
            val lado : Double = ladoCuadrado.text.toString().toDouble()
            val intent = Intent(this,Cuadrado::class.java)
            intent.putExtra("ladoC",lado)
            startActivity(intent)
        }

        btnTriangulo.setOnClickListener{
            val altura : Double = num1Triangulo.text.toString().toDouble()
            val base : Double = num2Triangulo.text.toString().toDouble()
            val intent = Intent(this,Triangulo::class.java)
            intent.putExtra("alturaT",altura)
            intent.putExtra("baseT",base)
        }

        btnCirculo.setOnClickListener{
            val radioC : Double = radio.text.toString().toDouble()
            val intent = Intent(this,Circulo::class.java)
            intent.putExtra("radioC",radioC)
        }

    }
}