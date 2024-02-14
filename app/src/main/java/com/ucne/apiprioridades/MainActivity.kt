package com.ucne.apiprioridades

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ucne.apiprioridades.ui.C_Prioridades.ConsultaScreen
import com.ucne.apiprioridades.ui.C_Prioridades.ConsultaViewModel
import com.ucne.apiprioridades.ui.R_Prioridades.RegistroScreen
import com.ucne.apiprioridades.ui.home.HomeScreen
import com.ucne.apiprioridades.ui.home.HomeViewModel
import com.ucne.apiprioridades.ui.theme.ApiPrioridadesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiPrioridadesTheme {
                // A surface container using the 'background' color from the theme
                setContent {
                    val viewModel: HomeViewModel = viewModel()
                    val ConsultaViewModel: ConsultaViewModel = viewModel()
                    var currentScreen by remember { mutableStateOf("home") }

                    if (currentScreen == "home") {
                        HomeScreen(
                            viewModel = viewModel,
                            onRegistro = { currentScreen = "registro" },
                            onConsulta = { currentScreen = "consulta" }
                        )
                    } else if (currentScreen == "registro") {
                        RegistroScreen(
                            onRegistrarButton = {}
                        )
                    } else if (currentScreen == "consulta") {
                        ConsultaScreen()
                    }
                }
            }
        }
    }
}
