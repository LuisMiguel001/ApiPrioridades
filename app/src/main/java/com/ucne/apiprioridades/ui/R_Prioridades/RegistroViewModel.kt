package com.ucne.apiprioridades.ui.R_Prioridades

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.apiprioridades.data.remote.dto.PrioridadDto
import com.ucne.apiprioridades.data.repository.PrioridadRepository
import com.ucne.apiprioridades.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroViewModel @Inject constructor(
    private val prioridadRepository: PrioridadRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RegistroState())
    val state = _state.asStateFlow()

    fun postPrioridades() {
        viewModelScope.launch {
            prioridadRepository.postPrioridades(_state.value.prioridades).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                succesMessage = "Se guardo correctamente"
                            )
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = result.message,
                                isLoading = false
                            )
                        }
                    }
                    else -> {}
                }
            }
        }
    }
    fun onEvent(event: PrioridadesEvent){
        when(event){
            is PrioridadesEvent.IdPrioridad -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(idPrioridad = event.idPrioridad.toIntOrNull()?:0)
                    )
                }
            }

            is PrioridadesEvent.Nombre -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(nombre = event.nombre)
                    )
                }
            }

            is PrioridadesEvent.Descripcion -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(descripcion = event.descripcion)
                    )
                }
            }

            is PrioridadesEvent.Plazo -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(plazo = event.plazo.toIntOrNull()?:0)
                    )
                }
            }

            is PrioridadesEvent.IsNull -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(esNulo = event.esNulo.toBoolean())
                    )
                }
            }

            is PrioridadesEvent.CreadoPor -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(Creador = event.creadoPor.toIntOrNull()?:0)
                    )
                }
            }

            is PrioridadesEvent.FechaCreacion -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(fechaCreacion = event.fechaChange)
                    )
                }
            }

            is PrioridadesEvent.ModificadoPor -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(modidicador = event.modificadoPor.toIntOrNull()?:0)
                    )
                }
            }

            is PrioridadesEvent.FechaModificaion -> {
                _state.update {
                    it.copy(
                        prioridades = it.prioridades.copy(fechaModificacion = event.fechaModificaion)
                    )
                }
            }

            PrioridadesEvent.onSave -> {
                postPrioridades()
                PrioridadesEvent.onNew
            }

            PrioridadesEvent.onNew -> {
                _state.update {
                    it.copy(
                        prioridades = PrioridadDto()
                    )
                }
            }
            else -> {}
        }
    }
}

data class RegistroState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val succesMessage: String? = null,
    val prioridades: PrioridadDto = PrioridadDto(),
)

sealed class PrioridadesEvent {
    data class IdPrioridad(val idPrioridad: String): PrioridadesEvent()
    data class Nombre(val nombre: String): PrioridadesEvent()
    data class Descripcion(val descripcion: String): PrioridadesEvent()
    data class Plazo(val plazo: String): PrioridadesEvent()
    data class IsNull(val esNulo: String): PrioridadesEvent()
    data class CreadoPor(val creadoPor: String): PrioridadesEvent()
    data class FechaCreacion(val fechaChange: String): PrioridadesEvent()
    data class ModificadoPor(val modificadoPor: String): PrioridadesEvent()
    data class FechaModificaion(val fechaModificaion: String): PrioridadesEvent()
    object onSave: PrioridadesEvent()
    object  onNew: PrioridadesEvent()
}
