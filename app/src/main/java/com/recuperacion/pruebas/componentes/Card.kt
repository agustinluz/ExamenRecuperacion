package com.recuperacion.pruebas.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.recuperacion.pruebas.modelo.ComponenteDieta
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults

@Composable
fun AlimentoCard(
    alimento:ComponenteDieta,
    onClick:(ComponenteDieta)->Unit,
    modifier : Modifier=Modifier
){

    Card(
        modifier = modifier.fillMaxSize()
            .padding(8.dp)
            .clickable { onClick(alimento) }, //Asi se llama cuando el card es clickado
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )


    ) {

        Column(modifier = Modifier.padding(16.dp)){
            Text(
                text = alimento.nombre,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "${alimento.calorias} KCAL",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}