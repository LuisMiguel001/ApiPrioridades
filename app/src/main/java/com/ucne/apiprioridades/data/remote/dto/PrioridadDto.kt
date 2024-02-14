package com.ucne.apiprioridades.data.remote.dto

import java.time.LocalDate

data class PrioridadDto(
    val idPrioridad : Int,
    val nombre: String,
    val descripcion: String,
    val plazo: Int,
    val esNulo: Boolean,
    val Creador: Int,
    val fechaCreacion: String,
    val modidicador: Int,
    val fechaModificacion: String,
)
