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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConsultaViewModel @Inject constructor(
    private val prioridadRepository: PrioridadRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ConsultaScreenState())
    val state = _state.asStateFlow()

    init {
        getAllPrioridades()
    }

    private fun getAllPrioridades() {
        viewModelScope.launch {
            prioridadRepository.getPrioridades().collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.update {
                            ConsultaScreenState(isLoading = true)
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                prioridad = result.data,
                                isLoading = false
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                error = result.message,
                                prioridad = emptyList(),
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
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