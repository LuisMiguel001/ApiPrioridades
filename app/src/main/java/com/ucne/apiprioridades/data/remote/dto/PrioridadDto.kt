package com.ucne.apiprioridades.data.remote.dto

import java.time.LocalDate

data class PrioridadDto(
    val idPrioridad : Int = 0,
    val nombre: String = "",
    val descripcion: String = "",
    val plazo: Int = 0,
    val esNulo: Boolean = false,
    val Creador: Int = 0,
    val fechaCreacion: String = "",
    val modidicador: Int = 0,
    val fechaModificacion: String = "",
)
