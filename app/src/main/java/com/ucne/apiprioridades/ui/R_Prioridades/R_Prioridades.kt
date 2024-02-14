package com.ucne.apiprioridades.ui.R_Prioridades

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ucne.apiprioridades.R
import com.ucne.apiprioridades.data.remote.dto.PrioridadDto
import kotlinx.coroutines.delay

@Composable
fun RegistroScreen(
    onRegistrarButton: (PrioridadDto) -> Unit
) {
    val viewModel: RegistroViewModel = viewModel()

    var idPrioridad by rememberSaveable { mutableStateOf("") }
    var nombre by rememberSaveable { mutableStateOf("") }
    var descripcion by rememberSaveable { mutableStateOf("") }
    var plazo by rememberSaveable { mutableStateOf("") }
    var isnull by rememberSaveable { mutableStateOf("") }
    var creador by rememberSaveable { mutableStateOf("") }
    var fechaCreacion by rememberSaveable { mutableStateOf("") }
    var modificadoPor by rememberSaveable { mutableStateOf("") }
    var fechaModificacion by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "Registro de Prioridad", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = idPrioridad,
            onValueChange = { idPrioridad = it },
            label = { Text("Prioridad ID") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next)
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = plazo.toString(),
            onValueChange = { plazo = it},
            label = { Text("Plazo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next)
        )

        OutlinedTextField(
            value = isnull.toString(),
            onValueChange = { isnull = it},
            label = { Text("EsNulo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = creador.toString(),
            onValueChange = { creador = it},
            label = { Text("Creado Por") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next)
        )

        OutlinedTextField(
            value = fechaCreacion,
            onValueChange = { fechaCreacion = it },
            label = { Text("Fecha de Creación") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = modificadoPor.toString(),
            onValueChange = { modificadoPor = it},
            label = { Text("Modificado Por") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next)
        )

        OutlinedTextField(
            value = fechaModificacion,
            onValueChange = { fechaModificacion = it },
            label = { Text("Fecha de Modificación") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Button(
            onClick = {
                val prioridad = PrioridadDto(
                    idPrioridad = idPrioridad.toInt(),
                    nombre = nombre,
                    descripcion = descripcion,
                    plazo = plazo.toInt(),
                    esNulo = isnull.toBoolean(),
                    Creador = creador.toInt(),
                    fechaCreacion = fechaCreacion,
                    modidicador = modificadoPor.toInt(),
                    fechaModificacion = fechaModificacion
                )
                viewModel.postPrioridades(prioridad)

              /*  idPrioridad = ""
                nombre = ""
                descripcion = ""
                plazo = ""
                creador = ""
                fechaCreacion = ""
                modificadoPor = ""
                fechaModificacion = ""*/
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Gray,
                containerColor = Color.Green
            )

        ) {
            Text(text = "Registrar", modifier = Modifier.padding(8.dp))
        }
    }
}
