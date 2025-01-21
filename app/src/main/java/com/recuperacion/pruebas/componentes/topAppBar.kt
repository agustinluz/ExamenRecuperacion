package com.recuperacion.pruebas.componentes

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiTopAppBar(
    onNavigateToFormulario: () -> Unit,
    onNavigateToListado: () -> Unit
) { TopAppBar(
        title = { Text(text = "GESTIÓN DE ALIMENTOS") },
        actions = {
            Button(onClick = onNavigateToFormulario) {
                Text(text = "Formulario")
            }
            Button(onClick = onNavigateToListado) {
                Text(text = "Listado")
            }
        }
    )
}


