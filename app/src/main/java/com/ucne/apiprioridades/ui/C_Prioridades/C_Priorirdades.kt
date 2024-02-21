package com.ucne.apiprioridades.ui.C_Prioridades

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ucne.apiprioridades.data.remote.dto.PrioridadDto
import com.ucne.apiprioridades.ui.home.HomeViewModel
import com.ucne.apiprioridades.ui.theme.ApiPrioridadesTheme

@Composable
fun ConsultaScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.homeState.collectAsState()

    ApiPrioridadesTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(0.dp, 50.dp),
                    strokeWidth = 8.dp
                )
            }

            LazyColumn(
                Modifier.fillMaxSize()
            ) {
                item {
                    Spacer(modifier = Modifier.padding(0.dp, 10.dp))
                }
                uiState.prioridades?.forEach { prioridad ->
                    item {
                        ListPrioridades(prioridad = prioridad)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListPrioridades(prioridad: PrioridadDto) {
    ElevatedCard(
        onClick = { /* Puedes manejar la acción de hacer clic aquí */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp, 5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(text = "PrioridadId: ${prioridad.idPrioridad}")
                Text(text = "Nombre: ${prioridad.nombre}")
                Text(text = "Descripción: ${prioridad.descripcion}")
                Text(text = "Plazo: ${prioridad.plazo}")
                Text(text = "EsNulo: ${prioridad.esNulo}")
                Text(text = "Creado Por: ${prioridad.Creador}")
                Text(text = "Fecha de Creación: ${prioridad.fechaCreacion}")
                Text(text = "Modificado Por: ${prioridad.modidicador}")
                Text(text = "Fecha de Modificación: ${prioridad.fechaModificacion}")
            }
        }
    }
    Spacer(modifier = Modifier.padding(5.dp))
}