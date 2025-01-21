package com.recuperacion.pruebas.screens

sealed class Ruta(val ruta:String) {
    object Formulario:Ruta("formulario")
    object ListadoDetalle:Ruta("listas")

}

