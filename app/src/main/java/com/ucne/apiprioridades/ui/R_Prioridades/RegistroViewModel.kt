package com.ucne.apiprioridades.ui.R_Prioridades

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.apiprioridades.data.remote.dto.PrioridadDto
import com.ucne.apiprioridades.data.repository.PrioridadRepository
import com.ucne.apiprioridades.ui.home.HomeState
import com.ucne.apiprioridades.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistroViewModel @Inject constructor(
    private val prioridadRepository: PrioridadRepository
) : ViewModel() {

    private val _registroState: MutableStateFlow<RegistroState> =
        MutableStateFlow(RegistroState())

    val registroState: StateFlow<RegistroState>
        get() = _registroState

    fun postPrioridades(prioridad: PrioridadDto) {
        viewModelScope.launch {
            prioridadRepository.postPrioridades(prioridad).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _registroState.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _registroState.update {
                            it.copy(
                                prioridades = PrioridadDto(
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
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Error -> {
                        _registroState.value = RegistroState(error = resource.message ?: "Error")
                    }
                }
            }
        }
    }
}

data class RegistroState(

    val isLoading: Boolean = false,
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