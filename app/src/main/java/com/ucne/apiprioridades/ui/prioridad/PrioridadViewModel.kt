package com.ucne.apiprioridades.ui.prioridad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.apiprioridades.data.remote.dto.PrioridadDto
import com.ucne.apiprioridades.data.repository.PrioridadRepository
import com.ucne.apiprioridades.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

@HiltViewModel
class PrioridadViewModel @Inject constructor(
    private val prioridad: PrioridadRepository
) : ViewModel() {

    private val _prioridadListState: MutableStateFlow<PrioridadListState> =
        MutableStateFlow(PrioridadListState())

    val prioridadListState: StateFlow<PrioridadListState>
        get() = _prioridadListState
    init {
        getAllPrioridades()
    }
    fun getAllPrioridades() {
        viewModelScope.launch {
            prioridad.getPrioridades().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _prioridadListState.value = PrioridadListState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _prioridadListState.value = PrioridadListState(prioridad = resource.data)
                    }
                    is Resource.Error -> {
                        _prioridadListState.value =
                            PrioridadListState(error = resource.message ?: "Error")
                    }
                }
            }
        }
    }

    fun getPrioridadById(prioridadId: Int) {
        viewModelScope.launch {
            prioridad.getPrioridadById(prioridadId).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        // Puedes manejar la carga si es necesario
                    }
                    is Resource.Success -> {
                        _prioridadListState.value =
                            PrioridadListState(selectPrioridadId = prioridadId, selectPrioridad = "Seleccionado")
                    }
                    is Resource.Error -> {
                        _prioridadListState.value =
                            PrioridadListState(error = resource.message)
                    }
                }
            }
        }
    }
}

data class PrioridadListState(
    val isLoading: Boolean = false,
    val prioridad: List<PrioridadDto>? = emptyList(),
    val error: String? = null,
    val selectPrioridad: String? = "",
    val selectPrioridadId: Int? = 0
)