package com.recuperacion.pruebas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.recuperacion.pruebas.modelo.ComponenteDieta
import com.recuperacion.pruebas.modelo.TipoComponente

class Alimento : ViewModel() {
    private val _nombre = MutableLiveData("")
    val nombre: LiveData<String> = _nombre

    private val _tipo = MutableLiveData(TipoComponente.SIMPLE)
    val tipo: LiveData<TipoComponente> = _tipo

    private val _cantidad = MutableLiveData(0.0)
    val cantidad: LiveData<Double> = _cantidad

    private val _grProtText = MutableLiveData("0.0")
    val grProtText: LiveData<String> = _grProtText

    private val _grHCText = MutableLiveData("0.0")
    val grHCText: LiveData<String> = _grHCText

    private val _grLipText = MutableLiveData("0.0")
    val grLipText: LiveData<String> = _grLipText

    private val _listaAlimentos = MutableLiveData<MutableList<ComponenteDieta>>(mutableListOf())
    val listaAlimentos: MutableLiveData<MutableList<ComponenteDieta>> = _listaAlimentos

    fun calculaKcal(): Double {
        val grProt = _grProtText.value?.toDoubleOrNull() ?: 0.0
        val grHC = _grHCText.value?.toDoubleOrNull() ?: 0.0
        val grLip = _grLipText.value?.toDoubleOrNull() ?: 0.0
        return 4 * grProt + 4 * grHC + 9 * grLip
    }

    fun guardarAlimento() {
        val nuevoAlimento = ComponenteDieta(
            nombre = _nombre.value ?: "",
            calorias = calculaKcal()
        )
        val lista = _listaAlimentos.value ?: mutableListOf()
        lista.add(nuevoAlimento)
        _listaAlimentos.value = lista
        resetAlimento()
    }

    fun resetAlimento() {
        _nombre.value = ""
        _cantidad.value = 0.0
        _grProtText.value = "0.0"
        _grHCText.value = "0.0"
        _grLipText.value = "0.0"
    }

    fun eliminarAlimento(alimento: ComponenteDieta) {


        listaAlimentos.removeObserver { alimento };
        listaAlimentos.value = listaAlimentos.value

    }

    fun setNombre(nombre: String) {
        _nombre.value = nombre
    }

    fun setCantidad(cantidad: String) {
        _cantidad.value = cantidad.toDoubleOrNull() ?: 0.0
    }

    fun setGrProtText(value: String) {
        _grProtText.value = value
    }

    fun setGrHCText(value: String) {
        _grHCText.value = value
    }

    fun setGrLipText(value: String) {
        _grLipText.value = value
    }

    fun setTipoComponente(tipo: TipoComponente) {
        _tipo.value = tipo
    }

    /**
     * // Reactivo con LiveData
     *     private val _numero3 = MutableLiveData(0)
     *     val numero3: LiveData<Int> get() = _numero3
     *
     *     fun incrementarNumero3() {
     *         _numero3.value = (_numero3.value ?: 0) + 1
     *     }*/
}
