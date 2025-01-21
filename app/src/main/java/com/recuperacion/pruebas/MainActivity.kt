package com.recuperacion.pruebas


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.recuperacion.pruebas.componentes.MiTopAppBar
import com.recuperacion.pruebas.componentes.NavigationButton
import com.recuperacion.pruebas.modelo.*
import com.recuperacion.pruebas.screens.Formulario
import com.recuperacion.pruebas.screens.ListadoDetalle
import com.recuperacion.pruebas.screens.Ruta
import com.recuperacion.pruebas.ui.theme.ExamenJPC_1Theme
import com.recuperacion.pruebas.viewmodel.Alimento


import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExamenJPC_1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        modifier = Modifier.padding(innerPadding), name = "GESTIÓN DE ALIMENTOS"
                    )
                }
            }
        }
    }
}
@Composable
fun Main(modifier: Modifier = Modifier, name: String) {

    val navigationController = rememberNavController()
    val viewModel: Alimento = androidx.lifecycle.viewmodel.compose.viewModel()

    Column(modifier.padding(20.dp)) {
        Scaffold(topBar = {
            MiTopAppBar(
                onNavigateToFormulario = { navigationController.navigate(Ruta.Formulario.ruta) },
                onNavigateToListado = { navigationController.navigate(Ruta.ListadoDetalle.ruta) }
            )
        }, content = { paddingValues ->
            NavHost(
                navController = navigationController,
                startDestination = Ruta.Formulario.ruta,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(Ruta.Formulario.ruta) {
                    Formulario(viewModel = viewModel, onNavigateToListado = {
                        navigationController.navigate(Ruta.ListadoDetalle.ruta)
                    })
                }
                composable(Ruta.ListadoDetalle.ruta) {
                    ListadoDetalle(viewModel = viewModel, onNavigateToFormulario = {
                        navigationController.navigate(Ruta.Formulario.ruta)
                    }, onNavigateBack = {
                        navigationController.popBackStack()
                    })
                }
            }
        })
    }
}
