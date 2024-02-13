package com.ucne.apiprioridades.data.remote.dto

data class PrioridadDto(
    val idPrioridad : Int,
    val nombre: String,
    var descripcion: String,
    val plazo: Int,
    var esNulo: Boolean,
    val Creador: Int,
    var fechaCreacion: String,
    val modidicador: Int,
    var fechaModificacion: String,
)
