package com.example.petagrampersistencia

data class Mascota(
    val id: Int,
    val nombre: String,
    val foto: Int,
    var rating: Int,
    var fechaRating: Long = 0
)
