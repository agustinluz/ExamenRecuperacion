package com.recuperacion.pruebas.modelo


import java.io.Serializable


data class ComponenteDieta(
    var nombre: String,
    var tipo: TipoComponente = TipoComponente.SIMPLE,
    var grHC_ini: Double = 0.0,
    var grLip_ini: Double = 0.0,
    var grPro_ini: Double = 0.0,
    val calorias: Double
) : Serializable {

    val grHC: Double
        get() = if (tipo.esSimpleOProcesado()) grHC_ini else ingredientes.sumOf { it.cd.grHC * it.cantidad / 100 }

    val grLip: Double
        get() = if (tipo.esSimpleOProcesado()) grLip_ini else ingredientes.sumOf { it.cd.grLip * it.cantidad / 100 }

    val grPro: Double
        get() = if (tipo.esSimpleOProcesado()) grPro_ini else ingredientes.sumOf { it.cd.grPro * it.cantidad / 100 }

    val Kcal: Double
        get() = (4 * grPro) + (4 * grHC) + (9 * grLip)

    val ingredientes: MutableList<Ingrediente> = mutableListOf()

    fun TipoComponente.esSimpleOProcesado() =
        this == TipoComponente.SIMPLE || this == TipoComponente.PROCESADO

    fun addIngrediente(ing: Ingrediente): Boolean {
        return if (!ingredientes.contains(ing)) {
            ingredientes.add(ing)
            true
        } else false
    }

    fun removeIngrediente(ing: Ingrediente): Boolean {
        return ingredientes.remove(ing)
    }
}

data class Ingrediente(
    var cd: ComponenteDieta,
    var cantidad: Double = 100.0
) : Serializable {}
