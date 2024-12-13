package com.example.appbbdd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AyudanteBBDD (context: Context) : SQLiteOpenHelper (context, NOMBRE_BBDD, null, VERSION_BBDD){
    companion object{
        const val NOMBRE_BBDD = "empresa.db"
        const val VERSION_BBDD = 1
        const val TABLA_USUARIOS = "usuarios"
        const val COLUMNA_ID = "id"
        const val COLUMNA_NOMBRE = "nombre"
        const val COLUMNA_CORREO = "email"

    }
    override fun onCreate(db: SQLiteDatabase) {
        val consultaCrearTabla = """
            CREATE TABLE $TABLA_USUARIOS(
            $COLUMNA_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMNA_NOMBRE TEXT NOT NULL,
            $COLUMNA_CORREO TEXT NOT NULL)
            """
        db.execSQL(consultaCrearTabla)
    }



    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLA_USUARIOS")
        onCreate(db)
    }

}