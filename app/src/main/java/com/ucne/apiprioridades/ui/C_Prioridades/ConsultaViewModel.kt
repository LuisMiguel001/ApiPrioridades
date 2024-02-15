package com.ucne.apiprioridades.ui.C_Prioridades

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.apiprioridades.data.remote.dto.PrioridadDto
import com.ucne.apiprioridades.data.repository.PrioridadRepository
import com.ucne.apiprioridades.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConsultaViewModel @Inject constructor(
    private val prioridadRepository: PrioridadRepository
) : ViewModel() {
//
    private val _state = MutableStateFlow(ConsultaScreenState())
    val state = _state.asStateFlow()

    init {
        getAllPrioridades()
    }

    fun getAllPrioridades() {
        viewModelScope.launch {
            prioridadRepository.getPrioridades().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _state.value = ConsultaScreenState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.update { it.copy(prioridad = resource.data, isLoading = false) }
                    }
                    is Resource.Error -> {
                        _state.value = ConsultaScreenState(error = resource.message ?: "Error desconocido")
                    }
                }
            }
        }
    }
/*
    fun getPrioridad(id: Int) {
        viewModelScope.launch {
            prioridadRepository.getPrioridadById(id).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _state.update { it.copy(selectedPrioridad = resource.data, isLoading = false) }
                    }
                    is Resource.Error -> {
                        _state.value = ConsultaScreenState(error = resource.message ?: "Error desconocido")
                        Log.e("ConsultaViewModel", "Error en la consulta: ${resource.message}")
                    }
                    is Resource.Loading -> {
                        _state.value = ConsultaScreenState(isLoading = true)
                        Log.d("ConsultaViewModel", "Cargando...")
                    }
                }
            }
        }
    }*/
}

data class ConsultaScreenState(
    val isLoading: Boolean = false,
    val prioridad: List<PrioridadDto>? = emptyList(),
    val prioridades: PrioridadDto? = PrioridadDto(
        0,
        "",
        "",
        0,
        false,
        0,
        "",
        0,
        "",
    ),
    val error: String? = null,
)