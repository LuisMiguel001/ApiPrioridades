package com.ucne.apiprioridades.ui.C_Prioridades

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ucne.apiprioridades.data.remote.dto.PrioridadDto

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ConsultaScreen(
    viewModel: ConsultaViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.getAllPrioridades()
    }

    /*var idPrioridad by remember { mutableStateOf("") }*/

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Consulta de Prioridades",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))
/*

        OutlinedTextField(
            value = idPrioridad,
            onValueChange = { idPrioridad = it },
            label = { Text("Buscar por ID") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )
*/

/*        Button(
            onClick = {
                if (idPrioridad.isNotEmpty()) {
                    viewModel.getPrioridad(idPrioridad.toInt())
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Gray,
                containerColor = Color.Green
            )
        ) {
            Text(text = "Buscar", modifier = Modifier.padding(8.dp))
        }*/

        Spacer(modifier = Modifier.height(16.dp))

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.wrapContentSize())
        } else {
            if (state.prioridad.isNullOrEmpty()) {
                Text(text = "No hay prioridades registradas.")
            } else {
                state.prioridad!!.forEach { prioridad ->
                    PrioridadItem(prioridad = prioridad)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        if (state.error != null) {
            Text(text = "Error: ${state.error}", color = Color.Red)
        }
    }
}

@Composable
fun PrioridadItem(prioridad: PrioridadDto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "ID: ${prioridad.idPrioridad}")
            Text(text = "Nombre: ${prioridad.nombre}")
            Text(text = "Descripción: ${prioridad.descripcion}")
            Text(text = "Creado Por: ${prioridad.Creador}")
            Text(text = "Fecha de Creación: ${prioridad.fechaCreacion}")
            Text(text = "Modificado Por: ${prioridad.modidicador}")
            Text(text = "Fecha de Modificación: ${prioridad.fechaModificacion}")
        }
    }
}