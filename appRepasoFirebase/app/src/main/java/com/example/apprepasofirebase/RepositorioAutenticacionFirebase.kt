package com.example.apprepasofirebase

import com.google.firebase.auth.FirebaseAuth

//Gestiona la autenticación con Firebase (iniciar sesión, registrar y cerrar sesión)

class RepositorioAutenticacionFirebase {
    private val autenticacionFirebase: FirebaseAuth = FirebaseAuth.getInstance()

    // Función para iniciar sesión con correo y contraseña
    fun iniciarSesion(correo: String, contrasenya: String, enResultado: (Boolean, String?)-> Unit){
        autenticacionFirebase.signInWithEmailAndPassword(correo, contrasenya)
            .addOnCompleteListener{ tarea ->
                if(tarea.isSuccessful) enResultado(true,null)
                else enResultado(false, tarea.exception?.localizedMessage)
            }
    }

    // Función para registrar un nuevo usuario
    fun registrar(correo: String, contrasenya: String, enResultado: (Boolean, String?) -> Unit){
        autenticacionFirebase.createUserWithEmailAndPassword(correo, contrasenya)
            .addOnCompleteListener{ tarea ->
                if(tarea.isSuccessful) enResultado(true,null)
                else enResultado(false, tarea.exception?.localizedMessage)
            }
    }

    // Función para cerrar sesión
    fun cerrarSesion(){
        autenticacionFirebase.signOut()
    }

    // obtener el usuario autenticado
    fun obtenerUsuarioActual() = autenticacionFirebase.currentUser
}