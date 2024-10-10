package com.example.apppropinas

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        btnCalcular.setOnClickListener {
            val edtPrecio = findViewById<EditText>(R.id.edtPrecio)
            val sRedondeo = findViewById<Switch>(R.id.sRedondeo)
            val txtTotal = findViewById<TextView>(R.id.txtTotal)
            val rG1 = findViewById<RadioGroup>(R.id.rG1)
            val rB20 = findViewById<RadioButton>(R.id.rB20)
            val rB18 = findViewById<RadioButton>(R.id.rB18)
            val rB15 = findViewById<RadioButton>(R.id.rB15)

            val porcentaje : Double
            if(rG1.checkedRadioButtonId == R.id.rB20){
                porcentaje = 1.2
            }else if(rG1.checkedRadioButtonId == R.id.rB18){
                porcentaje = 1.18
            }else{
                porcentaje = 1.15
            }

            var subTotal = edtPrecio.text.toString().toDouble()*porcentaje
            if(sRedondeo.isChecked){
                subTotal = ceil(subTotal)
            }
            txtTotal.setText(subTotal.toString())
        }

    }
}