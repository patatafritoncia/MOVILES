package com.example.apprepasofirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import com.example.apprepasofirebase.ui.theme.AppRepasoFirebaseTheme

// Integra y muestra la aplicación usando JectPack Compose y carga la navegación
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppRepasoFirebaseTheme {
                MaterialTheme{
                    NavegacionAplicacion(RepositorioAutenticacionFirebase())
                }
            }
        }
    }
}

@Composable
fun NavegacionAplicacion(repositorio: RepositorioAutenticacionFirebase){
    val navController = rememberNavController()

    NavHost(navController, startDestination = "login"){
        composable("login") {PantallaLogin(repositorio, navController)}
        composable("inicio") {PantallaInicio(repositorio, navController)}
    }
}

@Composable
fun PantallaLogin(repositorio: RepositorioAutenticacionFirebase, navController: NavController){
    var correo by remember{ mutableStateOf("") }
    var contrasenya by remember{ mutableStateOf("") }
    var mensajeError by remember{ mutableStateOf<String?>(null) }
    var cargando by remember{ mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Inicia Sesión", style = MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = correo,
            onValueChange = {correo = it},
            label = {Text("Correo")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = contrasenya,
            onValueChange = {contrasenya = it},
            label = {Text("Contraseña")},
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                cargando = true
                repositorio.iniciarSesion(correo,contrasenya){ exito, error ->
                    cargando = false
                    if(exito){
                        navController.navigate("inicio"){
                            popUpTo("login"){inclusive = true }
                        }
                    }else{
                        mensajeError = error ?: "Error al iniciar sesion"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Iniciar Sesión")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                cargando = true
                repositorio.registrar(correo, contrasenya){ exito, error ->
                    cargando = false
                    if(exito){
                        navController.navigate("inicio"){
                            popUpTo("login"){ inclusive = true}
                        }
                    }else {
                        mensajeError = error ?: "Error en el registro"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Registrarse")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if(cargando){
            CircularProgressIndicator()
        }
        mensajeError?.let { msg ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = msg, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
fun PantallaInicio(repositorio: RepositorioAutenticacionFirebase, navController: NavController){
    val usuario = repositorio.obtenerUsuarioActual()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Bienvenido, ${usuario?.email ?: "Usuario"}",
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            repositorio.cerrarSesion()
            navController.navigate("login"){
                popUpTo("inicio"){inclusive = true }
            }
        }){
            Text("Cerrar Sesión")
        }
    }
}


/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppRepasoFirebaseTheme {
        Greeting("Android")
    }
}*/