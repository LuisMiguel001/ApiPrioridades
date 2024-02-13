package com.ucne.apiprioridades.ui.prioridad

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ucne.apiprioridades.data.remote.dto.PrioridadDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrioridadScreem(
    prioridad: PrioridadDto
) {
    ElevatedCard(
        onClick = { },
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("ID: ${prioridad.idPrioridad}")
                Text("Nombre : ${prioridad.nombre}")
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Descripción: ${prioridad.descripcion}")
                    Text("Descripción: ${prioridad.plazo}")
                    Text("Descripción: ${prioridad.esNulo}")
                    Text("Descripción: ${prioridad.fechaCreacion}")
                    Text("Descripción: ${prioridad.Creador}")
                    Text("Descripción: ${prioridad.modidicador}")
                    Text("Descripción: ${prioridad.fechaModificacion}")
                }
            }
        }
        Spacer(modifier = Modifier.padding(2.dp))
    }
}