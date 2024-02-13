package com.ucne.apiprioridades.data.remote

import com.ucne.apiprioridades.data.remote.dto.PrioridadDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface PrioridadApi {

    @GET("/api/Prioridades")
    @Headers("X-API-Key: test")
    suspend fun getAllPrioridades():List<PrioridadDto>

    @GET("/api/Prioridades/{id}")
    @Headers("X-API-key: test")
    suspend fun getPrioridad(@Path("id") id: Int): PrioridadDto

    @POST("/api/Prioridades")
    @Headers("X-API-Key: test")
    suspend fun postPrioridades(@Body prioridad: PrioridadDto): Response<PrioridadDto>
}