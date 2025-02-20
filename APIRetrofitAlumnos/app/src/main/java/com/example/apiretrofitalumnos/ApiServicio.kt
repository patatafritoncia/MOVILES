package com.example.apiretrofitalumnos

import retrofit2.http.GET

interface ApiServicio {
    @GET("alumnos")
    suspend fun obtenerAlumnos():List<Alumno>
}