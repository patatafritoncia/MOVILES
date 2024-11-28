package com.example.appecuacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.appecuacion.ui.theme.AppEcuacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppEcuacionTheme {
                MiApp()
            }
        }
    }
}

@Composable
fun MiApp() {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        color = MaterialTheme.colorScheme.tertiary
    ){
        var entrada1 by remember{ mutableStateOf("") }
        var entrada2 by remember{ mutableStateOf("") }
        var entrada3 by remember{ mutableStateOf("") }
        var resultado1 by remember{ mutableStateOf(0.0) }
        var resultado2 by remember{ mutableStateOf(0.0) }

        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Row(modifier = Modifier.padding(50.dp),
                horizontalArrangement = Arrangement.Center){

                TextField(
                    modifier = Modifier.width(55.dp),
                    value = entrada1,
                    onValueChange = { entrada1 = it},
                    label = { Text("a")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Text(" X² + ", Modifier.padding(vertical = 15.dp))

                TextField(
                    modifier = Modifier.width(55.dp),
                    value = entrada2,
                    onValueChange = { entrada2 = it},
                    label = { Text("b")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Text(" X + ", Modifier.padding(vertical = 15.dp))

                TextField(
                    modifier = Modifier.width(55.dp),
                    value = entrada3,
                    onValueChange = { entrada3 = it},
                    label = { Text("c")},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Text(" = ", Modifier.padding(vertical = 15.dp))
            }

            Row(modifier = Modifier.padding(5.dp),
                verticalAlignment = Alignment.CenterVertically){
                Button(onClick = {
                    resultado1 = x1(entrada1.toDouble(), entrada2.toDouble(), entrada3.toDouble())
                    resultado2 = x2(entrada1.toDouble(), entrada2.toDouble(), entrada3.toDouble())
                },
                    colors = ButtonDefaults.buttonColors(Color.DarkGray),
                    modifier = Modifier.padding(5.dp)){
                    Text("Resultado")
                }
            }
            Row(modifier = Modifier.padding(5.dp),
                verticalAlignment = Alignment.CenterVertically){

                Text(text="X² =  ${resultado1}")

            }
            Row(modifier = Modifier.padding(5.dp),
                verticalAlignment = Alignment.CenterVertically){

                Text(text="X  =  ${resultado2}")

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VistaPreliminar() {
    AppEcuacionTheme {
            MiApp()
    }
}

fun x1(a: Double, b: Double, c: Double): Double {
    val discriminante = Math.pow(b, 2.0) - (4 * a * c)
    if (discriminante < 0) {
        throw ArithmeticException("El discriminante es negativo, no hay solución real.")
    }
    val resultado = (-b + (Math.sqrt(discriminante))) / (2 * a)
    return resultado
}

fun x2(a: Double, b: Double, c: Double): Double {
    val discriminante = Math.pow(b, 2.0) - (4 * a * c)
    if (discriminante < 0) {
        throw ArithmeticException("El discriminante es negativo, no hay solución real.")
    }
    val resultado = (-b - (Math.sqrt(discriminante))) / (2 * a)
    return resultado
}