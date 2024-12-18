package com.example.appbbddexamen

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AyudanteBaseDatos (context: Context) : SQLiteOpenHelper(context, NOMBRE_BASE_DATOS, null, VERSION_BASE_DATOS) {

    companion object {
        const val NOMBRE_BASE_DATOS = "clientes.db"
        const val VERSION_BASE_DATOS = 1

        const val TABLA_CLIENTES = "clientes"

        const val COLUMNA_ID = "id"
        const val COLUMNA_NOMBRE = "nombre"
        const val COLUMNA_APELLIDOS = "apellidos"
        const val COLUMNA_NUMCUENTA = "numero_cuenta"
        const val COLUMNA_DINERO = "dinero"
    }


    override fun onCreate(db: SQLiteDatabase) {
        val crearTablaUsuarios = """
            CREATE TABLE $TABLA_CLIENTES (
                $COLUMNA_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMNA_NOMBRE TEXT NOT NULL,
                $COLUMNA_APELLIDOS TEXT NOT NULL,
                $COLUMNA_NUMCUENTA TEXT NOT NULL,
                $COLUMNA_DINERO DOUBLE DEFAULT ''
            )
        """
        db.execSQL(crearTablaUsuarios)

        /*val insertarUsuarios = """
            INSERT INTO $TABLA_CLIENTES VALUES(1,"Cris","Tiano", "1234", 10.00)
        """
        db.execSQL(insertarUsuarios)*/
    }



    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLA_CLIENTES")
        onCreate(db)

    }
}
