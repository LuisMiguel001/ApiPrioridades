package com.ucne.apiprioridades

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ucne.apiprioridades.data.remote.dto.PrioridadDto
import com.ucne.apiprioridades.ui.prioridad.PrioridadScreem
import com.ucne.apiprioridades.ui.theme.ApiPrioridadesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiPrioridadesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PrioridadScreem(PrioridadDto(idPrioridad = 1, nombre = "Prueba", descripcion = "Prueba",
                        plazo = 1, esNulo = false, fechaCreacion = "Prueba", Creador = 1, modidicador =  1, fechaModificacion = "Prueba",))
                }
            }
        }
    }
}
