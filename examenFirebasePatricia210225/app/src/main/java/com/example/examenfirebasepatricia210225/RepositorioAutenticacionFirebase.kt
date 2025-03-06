package com.example.examenfirebasepatricia210225

import android.content.Intent
import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import android.util.Log
import android.widget.Toast

class RepositorioAutenticacionFirebase(private val activity: Activity, private val reqCode: Int) {

    private val autenticacionFirebase: FirebaseAuth = FirebaseAuth.getInstance()

    // Función para iniciar sesión con correo y contraseña
    fun iniciarSesion(correo: String, contrasenya: String, enResultado: (Boolean, String?) -> Unit) {
        autenticacionFirebase.signInWithEmailAndPassword(correo, contrasenya)
            .addOnCompleteListener { tarea ->
                if (tarea.isSuccessful) enResultado(true, null)
                else enResultado(false, tarea.exception?.localizedMessage)
            }
    }

    // Función para cerrar sesión
    fun cerrarSesion() {
        autenticacionFirebase.signOut()
    }

    // Función para obtener el usuario actual
    fun obtenerUsuarioActual(): FirebaseUser? {
        return autenticacionFirebase.currentUser
    }

    // Iniciar sesión con Google
    fun iniciarSesionGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("YOUR_WEB_CLIENT_ID") // Pon tu ID de cliente web de Firebase
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(activity, gso)
        val signInIntent: Intent = googleSignInClient.signInIntent
        activity.startActivityForResult(signInIntent, reqCode)
    }

    // Manejar el resultado de la autenticación de Google
    fun handleSignInResult(data: Intent?): FirebaseUser? {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential: AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
            autenticacionFirebase.signInWithCredential(credential)
                .addOnCompleteListener { tarea ->
                    if (tarea.isSuccessful) {
                        Log.d("GoogleSignIn", "signInWithCredential:success")
                    } else {
                        Log.w("GoogleSignIn", "signInWithCredential:failure", tarea.exception)
                    }
                }
            return autenticacionFirebase.currentUser
        } catch (e: ApiException) {
            Log.w("GoogleSignIn", "Google sign in failed", e)
            return null
        }
    }
}
