package com.recuperacion.pruebas.componentes



import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NavigationButton(
    onNavigateToFormulario: () -> Unit,
    onNavigateToListado: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = onNavigateToFormulario) {
            Text(text = "Formulario")
        }
        Button(onClick = onNavigateToListado) {
            Text(text = "Listado")
        }
    }
}

