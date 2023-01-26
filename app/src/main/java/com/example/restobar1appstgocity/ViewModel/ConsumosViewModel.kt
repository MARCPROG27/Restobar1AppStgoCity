package com.example.restobar1appstgocity.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.restobar1appstgocity.Model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ConsumosViewModel(application: Application) : AndroidViewModel(application){
    // CONEXIÓN CON EL RESPOSITORIO
    private val repository: ConsumosRepository

    // Live Data que expone la info del modelo
    val allTaskc: LiveData<List<ConsumosEntity>>

    init {

        // Necesito La instancia del repositorio

        val taskDao1 = ConsumosDatabase.getDatabase1(application).getConsumoDao()
        repository = ConsumosRepository(taskDao1)
        allTaskc = repository.listAllTaskc

    }

    // El viewmodel Scope trabaja con las coroutines  hace que se ejecute el proceso en el hilo secundario
    fun insertConsumo(consumo: ConsumosEntity) = viewModelScope.launch {
        repository.insertConsumo(consumo)
    }

    //maneja el borrado de todos los datos de la tabla consumo
    fun clearDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearDatabase()
        }

    }


    // maneja la actualización de los datos

    fun actualizarConsumo(consumo: ConsumosEntity) = viewModelScope.launch {
        repository.actualizarConsumo(consumo)
    }


    fun borrarConsumo(consumo: ConsumosEntity) = viewModelScope.launch {
        repository.borrarConsumo(consumo)
    }


    private var selectedConsumo: MutableLiveData<ConsumosEntity?> = MutableLiveData()

    // FUNCION PARA SELECCIONAR que puede ser null

    fun selected(consumo: ConsumosEntity?) {
        // GUARDAR LA TAREA SELECCIONADA
        selectedConsumo.value = consumo
    }

    // FUNCION PARA RECIBIR EL OBJETO SELECCIONADO
    fun selectedItemconsumo(): LiveData<ConsumosEntity?> = selectedConsumo

}