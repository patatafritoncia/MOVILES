package com.example.navegacionjetpackcompose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FirstScreen(){
    /*Un Scaffold es un elemento gráfico que sirve para estructurar los elementos mas tipicos
    de material design, una pantalla que tenga una barra superior, inferior y un cuerpo*/
    Scaffold (){
        BodyContent()
    }
}

@Composable
fun BodyContent(){
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("Hola Navegación")
        Button(onClick = {/*TODO*/}){
            Text("Navega")
        }
    }
}

@Preview (showBackground = true)
@Composable
fun Preview(){
    FirstScreen()
}