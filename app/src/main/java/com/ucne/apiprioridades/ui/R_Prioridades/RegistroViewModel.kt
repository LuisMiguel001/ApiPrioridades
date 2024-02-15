package com.ucne.apiprioridades.ui.R_Prioridades

import android.widget.Switch
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
                                succesMessege = "Se guardo correctamente"
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

            PrioridadesEvent.onSave -> {
                postPrioridades()
            }

            else -> {}
        }
    }
}



data class RegistroState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val succesMessege: String? = null,
    val prioridades: PrioridadDto = PrioridadDto(),
)

sealed class PrioridadesEvent {
    data class Nombre(val nombre: String): PrioridadesEvent()
    data class Descripcion(val descripcion: String): PrioridadesEvent()
    data class Plazo(val plazo: String): PrioridadesEvent()
    data class IsNull(val esNulo: String): PrioridadesEvent()
    data class CreadoPor(val creadoPor: String): PrioridadesEvent()
    data class FechaChange(val fechaChange: String): PrioridadesEvent()
    data class ModificadoPor(val modificadoPor: String): PrioridadesEvent()
    data class FechaModificaion(val fechaModificaion: String): PrioridadesEvent()
    object onSave: PrioridadesEvent()
    object  onNew: PrioridadesEvent()
}
