package com.ucne.apiprioridades.data.remote

import com.ucne.apiprioridades.data.remote.dto.PrioridadResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface PrioridadApi {

    @GET("/api/Tickets")
    @Headers("X-API-Key: test")
    suspend fun getTickets():List<PrioridadResponse>
}