package com.recuperacion.pruebas.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.recuperacion.pruebas.modelo.*
import com.recuperacion.pruebas.viewmodel.Alimento

@Composable
fun Formulario(viewModel: Alimento, onNavigateToListado: () -> Unit) {
    val nombre by viewModel.nombre.observeAsState("")
    val cantidad by viewModel.cantidad.observeAsState(0.0)
    val grProtText by viewModel.grProtText.observeAsState("0.0")
    val grHCText by viewModel.grHCText.observeAsState("0.0")
    val grLipText by viewModel.grLipText.observeAsState("0.0")
    val tipo by viewModel.tipo.observeAsState(TipoComponente.SIMPLE)
    val context = LocalContext.current
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Calorías totales: ${viewModel.calculaKcal()}")

        TextField(
            value = nombre,
            onValueChange = { viewModel.setNombre(it) },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = cantidad.toString(),
            onValueChange = { viewModel.setCantidad(it) },
            label = { Text("Cantidad (g)") },
            modifier = Modifier.fillMaxWidth()
        )

        TipoComponente.values().forEach { tipoComponente ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = tipoComponente == tipo,
                    onClick = { viewModel.setTipoComponente(tipoComponente) }
                )
                Text(text = tipoComponente.name, modifier = Modifier.padding(start = 8.dp))
            }
        }

        TextField(
            value = grProtText,
            onValueChange = { viewModel.setGrProtText(it) },
            label = { Text("Proteínas (g)") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = grHCText,
            onValueChange = { viewModel.setGrHCText(it) },
            label = { Text("Hidratos de Carbono (g)") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = grLipText,
            onValueChange = { viewModel.setGrLipText(it) },
            label = { Text("Lípidos (g)") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = {
                viewModel.guardarAlimento()
                onNavigateToListado()
                Toast.makeText(context, "Alimento guardado exitosamente", Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "Guardar")
            }
            Button(onClick = { viewModel.resetAlimento() }) {
                Text(text = "Cancelar")
            }
        }
    }
}
