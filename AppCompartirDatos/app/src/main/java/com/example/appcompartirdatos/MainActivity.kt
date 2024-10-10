package com.example.appcompartirdatos

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
        val edtMensaje = findViewById<EditText>(R.id.edtMensaje)
        val mensaje : String = edtMensaje.text.toString() // guardar el mensaje

        val btnDirecto = findViewById<Button>(R.id.btnDirecto)
        btnDirecto.setOnClickListener {
            val mensaje : String = edtMensaje.text.toString() // guardar el mensaje
            //Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show()

            val intent = Intent(this,Activity2::class.java)
            intent.putExtra("envio",mensaje) // envio no es string, contiene el valor del msg
                                                    // para la segunda actividad se llama envio
            startActivity(intent) // inciar actividad con intent
        }

        val btnElige = findViewById<Button>(R.id.btnElige)
        btnElige.setOnClickListener{
            val mensaje : String = edtMensaje.text.toString() // guardar el mensaje
            Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show()

            // serie de opciones
            val intent = Intent()
            intent.putExtra(Intent.EXTRA_TEXT,mensaje)
            intent.action = Intent.ACTION_SEND

            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share to"))

        }
    }
}