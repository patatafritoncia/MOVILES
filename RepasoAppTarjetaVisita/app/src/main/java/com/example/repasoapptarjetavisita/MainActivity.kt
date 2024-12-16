package com.example.repasoapptarjetavisita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.repasoapptarjetavisita.ui.theme.PurpleGrey90
import com.example.repasoapptarjetavisita.ui.theme.RepasoAppTarjetaVisitaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RepasoAppTarjetaVisitaTheme {
                CrearTarjetaVisita()
            }
        }
    }
}

@Composable
fun CrearTarjetaVisita(){
    var estadoBoton by remember{ mutableStateOf(false) }

        Surface(modifier = Modifier.fillMaxSize().fillMaxWidth()){
            Card(modifier = Modifier.width(200.dp)
                .height(390.dp)
                .padding(30.dp),
                shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                // Dentro de la carta metemos una funcion que contine una imagen
                Column (modifier = Modifier.height(300.dp).width(500.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally)
                {
                    fotoPerfil(modifier = Modifier)
                    HorizontalDivider(thickness = 5.dp)

                    // Metemos en una función nuestra info de perfil
                    infoPerfil()

                    Button(modifier = Modifier,
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onTertiaryContainer),
                        onClick = {
                        estadoBoton = !estadoBoton
                    }) {
                        Text("Portfolio", style = MaterialTheme.typography.labelLarge)
                    }
                }
                if(estadoBoton){
                    Contenido()
                }else{
                    Box(){

                    }
                }

            }
        }
}

@Composable
fun Contenido(){
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxSize().padding(5.dp)){
        Surface(modifier = Modifier.padding(3.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.Gray)
        ){
            Portfolio(data = listOf("Proyecto 1", "Proyecto 2", "Proyecto 3"))
        }

    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data){item ->
            Card(modifier = Modifier
                .padding(13.dp)
                .fillMaxWidth(),
                shape = RectangleShape,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onBackground)
            )
            {
                Row(modifier = Modifier.padding(8.dp)
                    .background(MaterialTheme.colorScheme.background)){
                    fotoPerfil(modifier = Modifier.size(50.dp))
                    Column(modifier = Modifier.padding(7.dp).align(alignment = Alignment.CenterVertically)){
                        Text(text = item, fontWeight = FontWeight.Bold)
                        Text(text = "Proyecto muy currado", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@Composable
private fun infoPerfil() {
    Column(
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Texto 1
        Text(
            text = "Bugs Bunny",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
        )

        // Texto 2
        Text(
            text = "¿Qué hay de nuevo viejo?",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.background
        )

        // Texto 3
        Text(
            text = "@bugsbunnyThebad87",
            style = MaterialTheme.typography.labelLarge,
            color = PurpleGrey90
        )
    }
}


// Función que contiene una imagen modificada
@Composable
private fun fotoPerfil(modifier: Modifier) {
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.tertiaryContainer),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    ) {
        // Dentro del segundo surface metemos una imagen
        Image(
            painter = painterResource(id = R.drawable.bugsbunny),
            contentDescription = "Imagen de perfil",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.size(135.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RepasoAppTarjetaVisitaTheme {
        CrearTarjetaVisita()
    }
}