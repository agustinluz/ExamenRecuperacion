package com.recuperacion.pruebas.componentes
import androidx.compose.material3.AlertDialog

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

//Ejemplo práctico de una función de orden superior a la que se le
//pasa una función lambda y que es definida en otro sitio.
//VENTAJA: Allí donde es definida puede usar las variables de aquel contexto
//y no tenemos que pasarselas a esta función como hemos hecho hasta ahora
@Composable
fun AlertDialogo(texto: String, lambda: (borrado: Boolean) -> Unit) {
    AlertDialog(
        onDismissRequest = { lambda(false) },
        confirmButton = {
            TextButton(onClick = { lambda(true) }) {
                Text("Eliminar")
            }
        },
        dismissButton = {
            TextButton(onClick = { lambda(false) }) {
                Text("Cancelar")
            }
        },
        text = {
            Text(texto)
        }
    )
}