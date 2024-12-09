package com.example.apptresenraya

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apptresenraya.ui.theme.AppTresEnRayaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTresEnRayaTheme {
                TresEnRaya()
            }
        }
    }
}

@Composable
fun TresEnRaya() {
    var posT00 by remember { mutableStateOf("") }
    var posT01 by remember { mutableStateOf("") }
    var posT02 by remember { mutableStateOf("") }
    var posT10 by remember { mutableStateOf("") }
    var posT11 by remember { mutableStateOf("") }
    var posT12 by remember { mutableStateOf("") }
    var posT20 by remember { mutableStateOf("") }
    var posT21 by remember { mutableStateOf("") }
    var posT22 by remember { mutableStateOf("") }

    var finJuego by remember { mutableStateOf(false) }

    var turno by remember { mutableStateOf("X") }

    fun onCellClick(row: Int, col: Int) {
        if (finJuego) return

        when (row to col) {
            0 to 0 -> if (posT00 == "") posT00 = turno
            0 to 1 -> if (posT01 == "") posT01 = turno
            0 to 2 -> if (posT02 == "") posT02 = turno
            1 to 0 -> if (posT10 == "") posT10 = turno
            1 to 1 -> if (posT11 == "") posT11 = turno
            1 to 2 -> if (posT12 == "") posT12 = turno
            2 to 0 -> if (posT20 == "") posT20 = turno
            2 to 1 -> if (posT21 == "") posT21 = turno
            2 to 2 -> if (posT22 == "") posT22 = turno
        }

        // Verificar ganador
        comprobarGanador(posT00, posT01, posT02, posT10, posT11, posT12, posT20, posT21, posT22)

        // Alternar turno
        if (!finJuego) {
            turno = if (turno == "X") "O" else "X"
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp).padding(vertical = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Fila 1
        Row (horizontalArrangement = Arrangement.spacedBy(10.dp)){
            Button(
                onClick = { onCellClick(0, 0) },
                modifier = Modifier.size(100.dp),
                enabled = !finJuego,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB6C1))
            ) {
                Text(text = posT00, fontSize = 32.sp)
            }
            Button(
                onClick = { onCellClick(0, 1) },
                modifier = Modifier.size(100.dp),
                enabled = !finJuego,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB6C1))
            ) {
                Text(text = posT01, fontSize = 32.sp)
            }
            Button(
                onClick = { onCellClick(0, 2) },
                modifier = Modifier.size(100.dp),
                enabled = !finJuego,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB6C1))
            ) {
                Text(text = posT02, fontSize = 32.sp)
            }
        }

        // Fila 2
        Row (horizontalArrangement = Arrangement.spacedBy(10.dp)){
            Button(
                onClick = { onCellClick(1, 0) },
                modifier = Modifier.size(100.dp),
                enabled = !finJuego,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB6C1))
            ) {
                Text(text = posT10, fontSize = 32.sp)
            }
            Button(
                onClick = { onCellClick(1, 1) },
                modifier = Modifier.size(100.dp),
                enabled = !finJuego,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB6C1))
            ) {
                Text(text = posT11, fontSize = 32.sp)
            }
            Button(
                onClick = { onCellClick(1, 2) },
                modifier = Modifier.size(100.dp),
                enabled = !finJuego,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB6C1))
            ) {
                Text(text = posT12, fontSize = 32.sp)
            }
        }

        // Fila 3
        Row (horizontalArrangement = Arrangement.spacedBy(10.dp)){
            Button(
                onClick = { onCellClick(2, 0) },
                modifier = Modifier.size(100.dp),
                enabled = !finJuego,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB6C1))
            ) {
                Text(text = posT20, fontSize = 32.sp)
            }
            Button(
                onClick = { onCellClick(2, 1) },
                modifier = Modifier.size(100.dp),
                enabled = !finJuego,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB6C1))
            ) {
                Text(text = posT21, fontSize = 32.sp)
            }
            Button(
                onClick = { onCellClick(2, 2) },
                modifier = Modifier.size(100.dp),
                enabled = !finJuego,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFB6C1))
            ) {
                Text(text = posT22, fontSize = 32.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar el turno actual
        Text(text = "Turno: $turno", fontSize = 24.sp)

        // Espacio para un botón de reinicio
        Spacer(modifier = Modifier.height(16.dp))

        /*
        // Botón para reiniciar el juego
        Button(onClick = {
            // Reinicia el tablero y el estado del juego
            posT00 = ""
            posT01 = ""
            posT02 = ""
            posT10 = ""
            posT11 = ""
            posT12 = ""
            posT20 = ""
            posT21 = ""
            posT22 = ""
            finJuego = false
            turno = "X" // Comienza con X
        }) {
            Text("Reiniciar Juego")
        }*/
    }
}

// Función para verificar si alguien ha ganado
fun comprobarGanador(
    posT00: String, posT01: String, posT02: String,
    posT10: String, posT11: String, posT12: String,
    posT20: String, posT21: String, posT22: String
) {
    // Verificar filas
    if (posT00 == "X" && posT01 == "X" && posT02 == "X" ||
        posT10 == "X" && posT11 == "X" && posT12 == "X" ||
        posT20 == "X" && posT21 == "X" && posT22 == "X" ||
        posT00 == "O" && posT01 == "O" && posT02 == "O" ||
        posT10 == "O" && posT11 == "O" && posT12 == "O" ||
        posT20 == "O" && posT21 == "O" && posT22 == "O") {
        println("¡Ha ganado!")
        return
    }

    // Verificar columnas
    if (posT00 == "X" && posT10 == "X" && posT20 == "X" ||
        posT01 == "X" && posT11 == "X" && posT21 == "X" ||
        posT02 == "X" && posT12 == "X" && posT22 == "X" ||
        posT00 == "O" && posT10 == "O" && posT20 == "O" ||
        posT01 == "O" && posT11 == "O" && posT21 == "O" ||
        posT02 == "O" && posT12 == "O" && posT22 == "O") {
        println("¡Ha ganado!")
        return
    }

    // Verificar diagonales
    if (posT00 == "X" && posT11 == "X" && posT22 == "X" ||
        posT02 == "X" && posT11 == "X" && posT20 == "X" ||
        posT00 == "O" && posT11 == "O" && posT22 == "O" ||
        posT02 == "O" && posT11 == "O" && posT20 == "O")
    {
        //Toast.makeText(context,"Has ganado", Toast.LENGTH_SHORT).show()
        println("¡Ha ganado!")
        return
    }

    // Si no hay un ganador, verificar si el tablero está lleno
    if (posT00 != "" && posT01 != "" && posT02 != "" &&
        posT10 != "" && posT11 != "" && posT12 != "" &&
        posT20 != "" && posT21 != "" && posT22 != "") {
        println("¡Empate!")
    }
}

@Preview(showBackground = true)
@Composable
fun VistaPreliminar() {
    AppTresEnRayaTheme {
        TresEnRaya()
    }
}

