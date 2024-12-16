package com.example.videosjetpackcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.videosjetpackcompose.ui.theme.VideosJetPackComposeTheme

private val messages: List<MyMessage> = listOf(
    MyMessage("¡Hola JetPack Compose!", "¿Preparado Con esta solución, el error relacionado con la medición de restricciones infinitas debe resolverse, y el desplazamiento de la lista funcionará correctamente. Si tienes más preguntas o algún otro problema, no dudes en preguntar. ¡Estoy aquí para ayudarte!?"),
    MyMessage("¡Hola JetPack Compose!", "¿Preparado Con esta solución, el error relacionado con la medición de restricciones infinitas debe resolverse, y el desplazamiento de la lista funcionará correctamente. Si tienes más preguntas o algún otro problema, no dudes en preguntar. ¡Estoy aquí para ayudarte!?"),
    MyMessage("¡Hola JetPack Compose!", "¿Preparado?"),
    MyMessage("¡Hola JetPack Compose!", "¿Preparado?"),
    MyMessage("¡Hola JetPack Compose!", "¿Preparado?"),
    MyMessage("¡Hola JetPack Compose!", "¿Preparado?"),
    MyMessage("¡Hola JetPack Compose!", "¿Preparado?"),
    MyMessage("¡Hola JetPack Compose!", "¿Preparado?"),
    MyMessage("¡Hola JetPack Compose!", "¿Preparado?"),
    MyMessage("¡Hola JetPack Compose!", "¿Preparado?"),
    MyMessage("¡Hola JetPack Compose!", "¿Preparado?"),
    MyMessage("¡Hola JetPack Compose!", "¿Preparado?"),
    MyMessage("¡Hola JetPack Compose!", "¿Preparado?"),
    MyMessage("¡Hola JetPack Compose!", "¿Preparado?"),
    MyMessage("¡Hola JetPack Compose!", "¿Preparado?"),
    MyMessage("¡Hola JetPack Compose!", "¿Preparado?"),
    MyMessage("¡Hola JetPack Compose!", "¿Preparado?"),
    MyMessage("¡Hola JetPack Compose!", "¿Preparado?"),
    MyMessage("¡Hola JetPack Compose!", "¿Preparado?")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            VideosJetPackComposeTheme {
                MyMessages(messages)
            }
            // elementos Composable, que van a formar comformar la interfaz de usuario de la app
            // y que van a ser utilizados, si lo pones aqui y le das al run se ve, sino no

        }

    }
}

data class MyMessage(val title: String, val body: String)

@Composable
fun MyMessages(messages : List<MyMessage>){
    LazyColumn {
        items(messages) {message ->
            MyComponent(message)
        }
    }
}

@Composable
fun MyComponent(message: MyMessage){
    Row(modifier = Modifier.background(MaterialTheme.colorScheme.background).padding(8.dp)){
        MyImage()
        MyTexts(message)
    }
}

@Composable
fun MyImage(){
    Image(
        painterResource(R.drawable.ic_launcher_foreground), "Imagen de prueba",
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)

    )
}

@Composable
fun MyTexts(message : MyMessage){
    // usamos remember para almacenar valores en una variable
    var expanded by remember { mutableStateOf(false) }

    Column (modifier = Modifier.padding(start = 8.dp).clickable {
        expanded = !expanded // esto es para cambiarle el valor cuando demos click de true a false y viceversa
    }){
        MyText(message.title,
            MaterialTheme.colorScheme.primary,
            MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        MyText(message.body,
            MaterialTheme.colorScheme.onBackground,
            MaterialTheme.typography.bodyMedium,
            if (expanded) Int.MAX_VALUE else 1
        )
    }

}

@Composable
fun MyText(text: String, color : Color, style : TextStyle, lines : Int = Int.MAX_VALUE){ // Recibe un maximo de linas que mostrar
    Text(text, color = color, style = style, maxLines = lines) // la propiedad style (azul) la igualamos al nuevo parametro style (gris)
}

@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewComponent(){
    VideosJetPackComposeTheme {
        val scrollState = rememberScrollState()
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp)
            .verticalScroll(scrollState)) {
        }

        MyMessages(messages)
    }

}

//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)


