package com.example.appcambiovistas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appcambiovistas.ui.theme.AppCambioVistasTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppCambioVistasTheme {
                NavegacionEntreVentanas()
            }
        }
    }
}

@Composable
fun NavegacionEntreVentanas(){
    val controladorNavegacion = rememberNavController()
    NavHost(navController = controladorNavegacion, startDestination = "inicio"){
        composable("inicio"){MiApp(controladorNavegacion)}
        composable("detalles"){ DetallesProducto(controladorNavegacion) }
    }
}

@Composable
fun DetallesProducto(navController: NavController){
    Column(){
        Text("Esto son los detalles cuando pinchas en el botón")
    }
}

@Composable
fun MiApp(navController: NavHostController) {
    Surface(){
        Column{
            Button(onClick = {
                navController.navigate("detalles")

            }){
                Text("Ir a detalles del producto")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VistaPreliminar() {
    AppCambioVistasTheme {
       NavegacionEntreVentanas()
    }
}