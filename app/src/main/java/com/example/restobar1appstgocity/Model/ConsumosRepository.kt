package com.example.restobar1appstgocity.Model

import androidx.lifecycle.LiveData

class ConsumosRepository (private val consumosDao:ConsumosDao) {


    val listAllTaskc: LiveData<List<ConsumosEntity>> = consumosDao.mostrartodoslosconsumos()

    suspend fun insertConsumo(consumo: ConsumosEntity) {
        consumosDao.insertconsumo(consumo)
    }


    // funcion que actualiza
    suspend fun actualizarConsumo(consumo: ConsumosEntity) {
        consumosDao.actualizarConsumo(consumo)

    }


    suspend fun borrarConsumo(consumo: ConsumosEntity) {
        consumosDao.borrarConsumo(consumo)


    }

    suspend fun clearDatabase() {
        consumosDao.clearDatabase()
    }


}