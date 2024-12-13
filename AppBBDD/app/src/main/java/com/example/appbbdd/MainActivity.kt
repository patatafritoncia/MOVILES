package com.example.appbbdd

import android.content.ContentValues
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.appbbdd.ui.theme.AppBBDDTheme



class MainActivity : ComponentActivity() {
    private lateinit var ayudanteBaseDatos : AyudanteBBDD
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa el ayudante de base de datos
        ayudanteBaseDatos =AyudanteBBDD(this)

        setContent {
            AppBBDDTheme {
                MiApp(ayudante = ayudanteBaseDatos)
            }
        }
    }
}

fun insertarUsuario(ayudante: AyudanteBBDD, nombre: String, email: String): Long{
    val db = ayudante.writableDatabase
    val valores = ContentValues().apply{
        put(AyudanteBBDD.COLUMNA_NOMBRE, nombre)
        put(AyudanteBBDD.COLUMNA_CORREO, email)
        //put(dbHelper.getEmail(), email)
    }
    return db.insert(AyudanteBBDD.TABLA_USUARIOS, null,valores)
}

fun eliminarUsuario (ayudante : AyudanteBBDD, id: Int): Int{
    val db = ayudante.writableDatabase
    return db.delete(
        AyudanteBBDD.TABLA_USUARIOS,
        "${AyudanteBBDD.COLUMNA_ID} = ?",
        arrayOf(id.toString())
    )
}

fun actualizarUsuario(ayudante: AyudanteBBDD, id: Int, nombre: String, correo: String): Int{
    val db = ayudante.writableDatabase
    val valores = ContentValues().apply {
        put (AyudanteBBDD.COLUMNA_NOMBRE, nombre)
        put (AyudanteBBDD.COLUMNA_CORREO, correo)
    }
    return db.update(
        AyudanteBBDD.TABLA_USUARIOS,
        valores,
        "${AyudanteBBDD.COLUMNA_ID} = ?",
        arrayOf((id.toString()))
    )
}

fun obtenerTodosLosResultados(ayudante: AyudanteBBDD): List<Triple<Int, String, String>>{
    val db = ayudante.readableDatabase
    val cursor = db.query(
        AyudanteBBDD.TABLA_USUARIOS,
        arrayOf(AyudanteBBDD.COLUMNA_ID, AyudanteBBDD.COLUMNA_NOMBRE, AyudanteBBDD.COLUMNA_CORREO),
        null,null,null,null,null
    )

    val usuarios = mutableListOf<Triple<Int, String, String>>()
    while (cursor.moveToNext()){
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(AyudanteBBDD.COLUMNA_ID))
        val nombre = cursor.getString(cursor.getColumnIndexOrThrow(AyudanteBBDD.COLUMNA_NOMBRE))
        val correo = cursor.getString(cursor.getColumnIndexOrThrow(AyudanteBBDD.COLUMNA_CORREO))
        usuarios.add(Triple(id, nombre, correo))
    }
    cursor.close()
    return usuarios
}

@Composable
fun MiApp(ayudante: AyudanteBBDD) {

}

@Preview(showBackground = true)
@Composable
fun VistaPreliminar() {
    AppBBDDTheme {

    }
}