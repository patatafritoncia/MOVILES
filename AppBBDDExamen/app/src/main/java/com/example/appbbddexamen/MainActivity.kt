package com.example.appbbddexamen

import android.content.ContentValues
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appbbddexamen.AyudanteBaseDatos.Companion.TABLA_CLIENTES
import com.example.appbbddexamen.ui.theme.AppBBDDExamenTheme

class MainActivity : ComponentActivity() {
    private lateinit var ayudanteBaseDatos: AyudanteBaseDatos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Intancia de la clase AyudanteBaseDatos
        ayudanteBaseDatos = AyudanteBaseDatos(this)
        setContent {
            AppBBDDExamenTheme {
                MiAplicacion(ayudante = ayudanteBaseDatos)
            }
        }
    }
}


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MiAplicacion(ayudante: AyudanteBaseDatos) {

        val clienteCris = Cliente(1,"Cris","Tiano","1234",200.0)
        val clienteAlba = Cliente(2,"Alba","Sura","02468",3000.0)
        val clienteAndres = Cliente(3,"Andres","Trozado","13579",150.0)
        val clienteInes = Cliente(4,"Ines","Tornudo","159753",1500.0)
        val clienteZacarias = Cliente(5,"Zacarias","Flores del Campo","987654",51000.0)

        insertarCliente(ayudante,clienteCris)
        insertarCliente(ayudante,clienteAlba)
        insertarCliente(ayudante,clienteAndres)
        insertarCliente(ayudante,clienteInes)
        insertarCliente(ayudante,clienteZacarias)

        // Lista de usuarios
        val clientes = remember { mutableStateListOf<Cliente>() }

        // Estado para el modo edición
        var enModoEdicion by remember { mutableStateOf(false) }
        var usuarioSeleccionado by remember { mutableStateOf(Cliente(0, "", "", "", 0.0)) }

        // Cargar los usuarios desde la base de datos al iniciar
        LaunchedEffect(Unit) {
            clientes.addAll(obtenerTodosLosUsuarios(ayudante))
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Gestión de Clientes") },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
                )
            },

            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    // Formulario para agregar o editar usuarios
                    PantallaAgregarUsuario(
                        cliente = usuarioSeleccionado,
                        enModoEdicion = enModoEdicion,
                        onUsuarioCambio = { usuarioSeleccionado = it },
                        onGuardar = {
                            if (enModoEdicion) {
                                // Actualizar usuario existente
                                actualizarCliente(ayudante, usuarioSeleccionado)
                                val index =
                                    clientes.indexOfFirst { it.id == usuarioSeleccionado.id }
                                if (index != -1) {
                                    clientes[index] = usuarioSeleccionado
                                }
                            } else {
                                // Agregar nuevo usuario
                                val id = insertarCliente(ayudante, usuarioSeleccionado).toInt()
                                clientes.add(usuarioSeleccionado.copy(id = id))
                            }
                            // Reiniciar el formulario
                            enModoEdicion = false
                            usuarioSeleccionado = Cliente(0, "", "", "", 0.0)
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Lista de usuarios
                    PantallaListaUsuarios(
                        clientes = clientes,
                        onActualizar = { cliente ->
                            enModoEdicion = true
                            usuarioSeleccionado = cliente
                        },
                        onEliminar = { cliente ->
                            eliminarCliente(ayudante, cliente.id)
                            clientes.remove(cliente)
                        }
                    )
                }
            }
        )
    }

    @Composable
    fun PantallaAgregarUsuario(
        cliente: Cliente,
        enModoEdicion: Boolean,
        onUsuarioCambio: (Cliente) -> Unit,
        onGuardar: () -> Unit
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextField(
                value = cliente.id.toString(),
                onValueChange = { onUsuarioCambio(cliente.copy(id = it.toIntOrNull() ?: 0)) },
                label = { Text("Id") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = cliente.nombre,
                onValueChange = { onUsuarioCambio(cliente.copy(nombre = it)) },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = cliente.apellidos,
                onValueChange = { onUsuarioCambio(cliente.copy(apellidos = it)) },
                label = { Text("Apellidos") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = cliente.numCuenta,
                onValueChange = { onUsuarioCambio(cliente.copy(numCuenta = it)) },
                label = { Text("Numero de cuenta") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = cliente.dinero.toString(),
                onValueChange = { onUsuarioCambio(cliente.copy(dinero = it.toDoubleOrNull() ?: 0.0)) },
                label = { Text("Dinero") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onGuardar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (enModoEdicion) "Actualizar Usuario" else "Guardar Usuario")
            }
        }
    }


    @Composable
    fun PantallaListaUsuarios(
        clientes: List<Cliente>,
        onActualizar: (Cliente) -> Unit,
        onEliminar: (Cliente) -> Unit
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(clientes) { cliente ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(2f)
                    ) {
                        Text(text = "ID: ${cliente.id}")
                        Text(text = "Nombre: ${cliente.nombre}")
                        Text(text = "Apellidos: ${cliente.apellidos}")
                        Text(text = "Número de cuenta: ${cliente.numCuenta}")
                        Text(text = "Dinero: ${cliente.dinero}")
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = { onActualizar(cliente) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Editar")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { onEliminar(cliente) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red,
                                contentColor = Color.White
                            )
                        ) {
                            Text("Borrar")
                        }
                    }
                }
            }
        }
    }



    /* -------------      FUNCIONES       -------------*/

    // Función para insertar un nuevo usuario
    fun insertarCliente(ayudante: AyudanteBaseDatos, cliente: Cliente): Long {

        val db = ayudante.readableDatabase
        val valores = ContentValues().apply {
            put(AyudanteBaseDatos.COLUMNA_ID, cliente.id)
            put(AyudanteBaseDatos.COLUMNA_NOMBRE, cliente.nombre)
            put(AyudanteBaseDatos.COLUMNA_APELLIDOS, cliente.apellidos)
            put(AyudanteBaseDatos.COLUMNA_NUMCUENTA, cliente.numCuenta)
            put(AyudanteBaseDatos.COLUMNA_DINERO, cliente.dinero)
        }
        return db.insert(AyudanteBaseDatos.TABLA_CLIENTES, null, valores)
    }


/*fun inicioApp(ayudante: AyudanteBaseDatos): Long {

    val db = ayudante.readableDatabase
    val valores = ContentValues().apply {
        put(AyudanteBaseDatos.COLUMNA_ID, 1)
        put(AyudanteBaseDatos.COLUMNA_NOMBRE, "Cris")
        put(AyudanteBaseDatos.COLUMNA_APELLIDOS, "Tiano")
        put(AyudanteBaseDatos.COLUMNA_NUMCUENTA, "1234")
        put(AyudanteBaseDatos.COLUMNA_DINERO, 34.50)
    }

    return db.insert(AyudanteBaseDatos.TABLA_CLIENTES, null, valores)
}*/

    fun eliminarCliente(ayudante: AyudanteBaseDatos, id: Int): Int {
        val db = ayudante.writableDatabase
        return db.delete(
            AyudanteBaseDatos.TABLA_CLIENTES,
            "${AyudanteBaseDatos.COLUMNA_ID} = ?",
            arrayOf(id.toString())
        )
    }


    // Función para actualizar los usuarios
    fun actualizarCliente(ayudante: AyudanteBaseDatos, cliente: Cliente): Int {
        val db = ayudante.writableDatabase
        val valores =
            ContentValues().apply {
                put(AyudanteBaseDatos.COLUMNA_NOMBRE, cliente.nombre)
                put(AyudanteBaseDatos.COLUMNA_APELLIDOS, cliente.apellidos)
                put(AyudanteBaseDatos.COLUMNA_NUMCUENTA, cliente.numCuenta)
                put(AyudanteBaseDatos.COLUMNA_DINERO, cliente.dinero)
            }
        return db.update(
            AyudanteBaseDatos.TABLA_CLIENTES,
            valores, "${AyudanteBaseDatos.COLUMNA_ID} = ?",
            arrayOf(cliente.id.toString())
        )
    }

    // Función para obtener todos los usuarios en una lista
    fun obtenerTodosLosUsuarios(ayudante: AyudanteBaseDatos): List<Cliente> {
        val db = ayudante.readableDatabase
        val cursor = db.query(
            AyudanteBaseDatos.TABLA_CLIENTES,
            arrayOf(
                AyudanteBaseDatos.COLUMNA_ID,
                AyudanteBaseDatos.COLUMNA_NOMBRE,
                AyudanteBaseDatos.COLUMNA_APELLIDOS,
                AyudanteBaseDatos.COLUMNA_NUMCUENTA,
                AyudanteBaseDatos.COLUMNA_DINERO
            ),
            null, null, null, null, null
        )

        val usuarios = mutableListOf<Cliente>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(AyudanteBaseDatos.COLUMNA_ID))
            val nombre =
                cursor.getString(cursor.getColumnIndexOrThrow(AyudanteBaseDatos.COLUMNA_NOMBRE))
            val apellidos =
                cursor.getString(cursor.getColumnIndexOrThrow(AyudanteBaseDatos.COLUMNA_APELLIDOS))
            val numCuenta = cursor.getString(cursor.getColumnIndexOrThrow(AyudanteBaseDatos.COLUMNA_NUMCUENTA))
            val dinero =
                cursor.getDouble(cursor.getColumnIndexOrThrow(AyudanteBaseDatos.COLUMNA_DINERO))
            usuarios.add(Cliente(id, nombre, apellidos, numCuenta, dinero))
        }
        cursor.close()
        return usuarios
    }



@Preview(showBackground = true)
@Composable
fun Preview() {
    AppBBDDExamenTheme {

    }
}