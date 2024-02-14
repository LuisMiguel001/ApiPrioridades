package com.ucne.apiprioridades.ui.home

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

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val prioridadRepository: PrioridadRepository
) : ViewModel() {

    private val _homeState: MutableStateFlow<HomeState> =
        MutableStateFlow(HomeState())

    val homeState: StateFlow<HomeState>
        get() = _homeState

    init {
        getAllPrioridades()
    }

    fun getAllPrioridades() {
        viewModelScope.launch {
            prioridadRepository.getPrioridades().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _homeState.value = HomeState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _homeState.value = HomeState(prioridades = resource.data)
                    }
                    is Resource.Error -> {
                        _homeState.value = HomeState(error = resource.message ?: "Error")
                    }
                }
            }
        }
    }
}

data class HomeState(
    val isLoading: Boolean = false,
    val prioridades: List<PrioridadDto>? = emptyList(),
    val error: String? = null,
)