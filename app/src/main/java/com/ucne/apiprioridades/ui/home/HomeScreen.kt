package com.ucne.apiprioridades.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onRegistro: () -> Unit,
    onConsulta: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "¡BIENVENIDO ALELAO!",
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onRegistro() },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.Gray
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                "Registrar Prioridad",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Button(
            onClick = { onConsulta() },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.Green
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                "Consultar Prioridad",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
