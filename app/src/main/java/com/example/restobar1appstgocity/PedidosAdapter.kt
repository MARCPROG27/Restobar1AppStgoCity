package com.example.restobar1appstgocity

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.restobar1appstgocity.Model.ConsumosEntity
import com.example.restobar1appstgocity.databinding.ConsumosItemBinding


class PedidosAdapter : RecyclerView.Adapter<PedidosAdapter.TaskVHConsumo>() {
    // lista de los datos
    private var mlistTaskEntity = listOf<ConsumosEntity>()


    private val selectedTaskEntity = MutableLiveData<ConsumosEntity>()

    // devuelve el elemento


    fun selectedItem(): LiveData<ConsumosEntity> = selectedTaskEntity

    // RECIBE UN LISTADO DE TAREAS
    fun update(listTaskEntity: List<ConsumosEntity>) {
        mlistTaskEntity = listTaskEntity
        notifyDataSetChanged()
    }


    // SE COMUNICA CON EL XML QUE TENDRA LA VISTA DEL RECYCLERVIEW
// puedo tener acceso dentro de la clase
    inner class TaskVHConsumo(private val binding: ConsumosItemBinding) :
        RecyclerView.ViewHolder(binding.root),
        // implemento el Clik
        View.OnClickListener {

        fun bind(consumo: ConsumosEntity) {

            //  binding. = productos.
            binding.txtconsumo.text = consumo.nomproducto.toString()
            binding.txtcantidadconsumo.text = consumo.valortotal.toString()

            itemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            selectedTaskEntity.value = mlistTaskEntity[adapterPosition]

        }
    }

    // infla la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVHConsumo {
        return TaskVHConsumo(ConsumosItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    // Entrega una posicion y une con el objeto por posicion
    override fun onBindViewHolder(holder: TaskVHConsumo, position: Int) {
        val taskEntity = mlistTaskEntity[position]
        holder.bind(taskEntity)


    }

    // Muestra total de los elementos
    override fun getItemCount(): Int = mlistTaskEntity.size



 }