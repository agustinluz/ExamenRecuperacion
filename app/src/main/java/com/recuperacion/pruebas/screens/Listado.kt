package com.recuperacion.pruebas.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.recuperacion.pruebas.componentes.AlertDialogo
import com.recuperacion.pruebas.componentes.AlimentoCard
import com.recuperacion.pruebas.modelo.ComponenteDieta
import com.recuperacion.pruebas.viewmodel.Alimento

@Composable
fun ListadoDetalle(
    viewModel: Alimento,
    onNavigateToFormulario: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val alimentos by viewModel.listaAlimentos.observeAsState(emptyList())
    var alimentoSeleccionado by remember { mutableStateOf<ComponenteDieta?>(null) } // Actualiza aquí
    var mostrarDialogo by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        alimentos.forEach { alimento ->
            AlimentoCard(
                alimento = alimento,
                onClick = {
                    selectedAlimento -> alimentoSeleccionado = selectedAlimento
                    mostrarDialogo=true
                }
            )
        }
    }

    if (mostrarDialogo && alimentoSeleccionado != null) {
        AlertDialogo(
            texto = "¿Quieres eliminar ${alimentoSeleccionado?.nombre}?",
            lambda = { borrado ->
                if (borrado) {
                    alimentoSeleccionado?.let { viewModel.eliminarAlimento(it) }
                }
                mostrarDialogo = false
                alimentoSeleccionado = null
            }
        )
    }
}

