package com.example.apiretrofit

import retrofit2.http.GET

interface ApiServicio {
    @GET("alumos")
    suspend fun obtenerAlumnos(): List<Alumno>
}