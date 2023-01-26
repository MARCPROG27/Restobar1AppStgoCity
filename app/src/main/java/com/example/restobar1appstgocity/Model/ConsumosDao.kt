package com.example.restobar1appstgocity.Model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ConsumosDao {
    //inserta una tarea OBJETO DEL LA ENTIDAD  , ESTRATEGIA DE CONFLICTO REPETICIÓN EJ ID
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend infix fun insertconsumo(consumoRestobar: ConsumosEntity)

    //inserta listado de tareas
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //suspend fun insertTodosProductos(listTask: List<ProductosEntity>)
    suspend fun insertTodosconsumos(listconsumoRestobar: List<ConsumosEntity>)

    //borrar una tarea
    @Delete
    suspend fun borrarConsumo(consumoRestobar: ConsumosEntity)

    // actualizar datos
    @Update
    suspend fun actualizarConsumo(consumoRestobar: ConsumosEntity)

    // traer todos los elementos de la tabla
    @Query("SELECT * FROM  consumo")
    //fun getAllconsumos(): List<ConsumoEntity>
    fun mostrartodoslosconsumos(): LiveData<List<ConsumosEntity>>

    // trae por nombre producto
    @Query("SELECT * FROM  consumo WHERE nomproducto=:nomproducto Limit 1")
    fun mostrarProductoConsumoporNombre(nomproducto: String): LiveData<ConsumosEntity>


    // trae una tarea por Id
    @Query("SELECT * FROM consumo WHERE id =:id")
    fun mostrarConsumoID(id: Int): LiveData<ConsumosEntity>

    //borra todos los registros de la tabla consumo
    @Query("DELETE FROM consumo")
    fun clearDatabase(): Int


}