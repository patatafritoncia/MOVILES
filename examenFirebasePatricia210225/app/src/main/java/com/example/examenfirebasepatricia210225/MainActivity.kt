package com.example.examenfirebasepatricia210225

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
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import com.example.examenfirebasepatricia210225.ui.theme.ExamenFirebasePatricia210225Theme
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MainActivity : ComponentActivity() {
    private val REQ_CODE_GOOGLE_SIGN_IN = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ExamenFirebasePatricia210225Theme {
                MaterialTheme {
                    NavegacionAplicacion(RepositorioAutenticacionFirebase(this, REQ_CODE_GOOGLE_SIGN_IN))
                }
            }
        }
    }
}

// Este es el método que debes colocar dentro de la clase MainActivity
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    // Verifica si el código de solicitud es el de la autenticación de Google
    if (requestCode == REQ_CODE_GOOGLE_SIGN_IN) {
        // Maneja el resultado del inicio de sesión con Google
        val user = repositorio.handleSignInResult(data)

        // Si la autenticación fue exitosa
        if (user != null) {
            // Navegar a la pantalla de inicio
            navController.navigate("inicio") {
                popUpTo("login") { inclusive = true }
            }
        } else {
            // Si hubo error en el inicio de sesión
            Toast.makeText(this, "Error en el inicio de sesión con Google", Toast.LENGTH_SHORT).show()
        }
    }
}
}

@Composable
fun NavegacionAplicacion(repositorio: RepositorioAutenticacionFirebase) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "login") {
        composable("login") { PantallaLogin(repositorio, navController) }
        composable("inicio") { PantallaInicio(repositorio, navController) }
    }
}

@Composable
fun PantallaLogin(repositorio: RepositorioAutenticacionFirebase, navController: NavController) {
    var correo by remember { mutableStateOf("") }
    var contrasenya by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf<String?>(null) }
    var cargando by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Inicia Sesión", style = MaterialTheme.typography.labelMedium)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = contrasenya,
            onValueChange = { contrasenya = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                cargando = true
                repositorio.iniciarSesion(correo, contrasenya) { exito, error ->
                    cargando = false
                    if (exito) {
                        navController.navigate("inicio") {
                            popUpTo("login") { inclusive = true }
                        }
                    } else {
                        mensajeError = error ?: "Error al iniciar sesión"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }

        // Botón de inicio de sesión con Google
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { repositorio.iniciarSesionGoogle() }
        ) {
            Text("Iniciar sesión con Google")
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (cargando) {
            CircularProgressIndicator()
        }

        mensajeError?.let { msg ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = msg, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
fun PantallaInicio(repositorio: RepositorioAutenticacionFirebase, navController: NavController) {
    val usuario = repositorio.obtenerUsuarioActual()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido, ${usuario?.email ?: "Usuario"}",
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            repositorio.cerrarSesion()
            navController.navigate("login") {
                popUpTo("inicio") { inclusive = true }
            }
        }) {
            Text("Cerrar Sesión")
        }
    }
}

