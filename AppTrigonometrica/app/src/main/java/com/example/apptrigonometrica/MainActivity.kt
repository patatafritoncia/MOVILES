package com.example.apptrigonometrica

import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.widget.NumberPicker.OnValueChangeListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apptrigonometrica.ui.theme.AppTrigonometricaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTrigonometricaTheme {
                MiApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiApp(){
    var ladoa by remember{mutableStateOf("")}
    var ladob by remember{mutableStateOf("")}

    // Resultados
    var hipotenusa by remember{ mutableStateOf("") }
    var anguloAlfa by remember{ mutableStateOf("") }
    var anguloBeta by remember{ mutableStateOf("") }
    var area by remember{ mutableStateOf("") }
    var perimetro by remember{ mutableStateOf("") }


    Column(horizontalAlignment = Alignment.CenterHorizontally){
        // Fila lado a
        Row(modifier = Modifier.padding(10.dp)){
            Text("Lado a")

            TextField(
                value = ladoa,
                onValueChange = { ladoa = it},
                label = { Text("a")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        // Fila lado b
        Row(modifier = Modifier.padding(10.dp)){
            Text("Lado b")

            TextField(
                value = ladob,
                onValueChange = { ladob = it},
                label = { Text("b")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        // Fila Hipotenusa
        Row(modifier = Modifier.padding(10.dp)){
            Text("Hipot c")

            TextField(
                value = hipotenusa,
                onValueChange = { hipotenusa = it},
                label = { Text("")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        // Fila Angulo Alfa
        Row(modifier = Modifier.padding(10.dp)){
            Text("Angulo Alfa")

            TextField(
                value = anguloAlfa,
                onValueChange = { anguloAlfa = it},
                label = { Text("")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        // fILA aNGULO bETA
        Row(modifier = Modifier.padding(10.dp)){
            Text("Angulo Beta")

            TextField(
                value = anguloBeta,
                onValueChange = { anguloBeta = it},
                label = { Text("")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        // Fila Area
        Row(modifier = Modifier.padding(10.dp)){
            Text("Angulo Beta")

            TextField(
                value = area,
                onValueChange = { area = it},
                label = { Text("")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        // Fila Perimetro
        Row(modifier = Modifier.padding(10.dp)){
            Text("Angulo Beta")

            TextField(
                value = perimetro,
                onValueChange = { perimetro = it},
                label = { Text("")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        Row(modifier = Modifier.padding(16.dp)){
            Button(onClick = {
                hipotenusa = calculoHipotenusa(ladoa.toDouble(), ladob.toDouble()).toString();
                anguloAlfa = anguloAlfaFun(ladoa.toDouble(), ladob.toDouble()).toString();
                anguloBetaFun(ladoa.toDouble(), ladob.toDouble()).toString();
                area = areaFun(ladoa.toDouble(), ladob.toDouble()).toString();

                             },
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier.padding(5.dp)
            ){
                Text("Calcular")
            }

            Button(onClick = {hipotenusa = "";
                             anguloAlfa = "";
                             anguloBeta = "";
                             area = "";
                             perimetro = "";
                             ladoa = "";
                             ladob = ""},
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier.padding(5.dp)){
                Text("Vacío")
            }
            // Button(){}
        }

    }

}


/* -----------FUNCIONES----------*/

fun calculoHipotenusa(a: Double, b: Double): Double{
    val suma = Math.pow(a,2.0) + Math.pow(b,2.0)
    val resultadoHipotenusa = Math.sqrt(suma)

    return resultadoHipotenusa
}

fun anguloAlfaFun (a: Double, b: Double): Double{
    val division = b/a
    val resultadoAlfa = Math.atan(division)
    return resultadoAlfa
}

fun anguloBetaFun (a: Double, b: Double): Double{
    val division = a/b
    val resultadoBeta = Math.atan(division)
    return resultadoBeta
}

fun areaFun (a: Double, b: Double): Double{
    val area = (a*b)/2
    return area
}

fun perimetroFun(a: Double, b: Double, c: Double): Double{
    val perimetro = a + b + c
    return perimetro
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    AppTrigonometricaTheme {
        MiApp()
    }
}

