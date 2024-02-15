package com.ucne.apiprioridades.data.repository

import com.ucne.apiprioridades.data.remote.PrioridadApi
import com.ucne.apiprioridades.data.remote.dto.PrioridadDto
import com.ucne.apiprioridades.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PrioridadRepository @Inject constructor(
    private val prioridad : PrioridadApi
){
    fun getPrioridades(): Flow<Resource<List<PrioridadDto>>> = flow {
        try {
            emit(Resource.Loading())

            val prioridades = prioridad.getAllPrioridades()

            delay(3000L)
            emit(Resource.Success(prioridades))

        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
        catch (e: Exception) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    fun getPrioridadById(prioridadId: Int): Flow<Resource<PrioridadDto>> = flow {
        try {
            emit(Resource.Loading())

            val prioridades = prioridad.getPrioridad(prioridadId)

            delay(3000L)
            emit(Resource.Success(prioridades))

        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
        catch (e: Exception) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    fun postPrioridades(prioridades: PrioridadDto): Flow<Resource<PrioridadDto>> = flow{
        try {
            emit(Resource.Loading())

            val response = prioridad.postPrioridades(prioridades)

        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
        catch (e: Exception) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }
}