package com.ucne.apiprioridades.data.remote.dto

import java.time.LocalDate

data class PrioridadDto(
    val idPrioridad : Int = 1,
    val nombre: String = "",
    val descripcion: String = "",
    val plazo: Int = 1,
    val esNulo: Boolean = false,
    val Creador: Int = 1,
    val fechaCreacion: String = "",
    val modidicador: Int = 1,
    val fechaModificacion: String = "",
)
