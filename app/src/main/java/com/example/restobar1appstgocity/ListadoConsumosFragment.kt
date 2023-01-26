package com.example.restobar1appstgocity

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restobar1appstgocity.Model.ConsumosDatabase
import com.example.restobar1appstgocity.Model.ConsumosEntity
import com.example.restobar1appstgocity.ViewModel.ConsumosViewModel
import com.example.restobar1appstgocity.databinding.FragmentListadoConsumosBinding
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListadoConsumosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListadoConsumosFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var _binding: FragmentListadoConsumosBinding


    private val viewModelConsumo: ConsumosViewModel by activityViewModels()

    private var idPedido: Int = 0
    private var taskSelected: ConsumosEntity? = null

    var idMars: String = ""


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListadoConsumosBinding.inflate(inflater, container, false)

        _binding.fbatras.setOnClickListener {
            findNavController().navigate(R.id.action_listadoConsumosFragment_to_pedidoConsumoFragment)
        }


        //al hacer clic en si va al metodo cleardatabase (borra todos los registros de la tabla consumo)
        binding.btneliminartodos.setOnClickListener {

            val binder = AlertDialog.Builder(requireContext())
            binder.setPositiveButton("Si") { _, _ -> clearDatabase() }
            binder.setNegativeButton("No") { _, _ -> }
            binder.setTitle("INFORMACION URGENTE DEL CONSUMO APP RESTOBAR STGO CITY")
            binder.setMessage("¿USTED QUIERE BORRAR TODO EL CONSUMO DE LA APP?")
            binder.create().show()

        }

        // referencia al Adapter
        val adapter = PedidosAdapter()
        binding.rvconsumos.adapter = adapter
        binding.rvconsumos.layoutManager = LinearLayoutManager(context)

        binding.rvconsumos.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )


        // recorre la base de datos Y OBSERVA SI HAY CAMBIOS
        viewModelConsumo.allTaskc.observe(viewLifecycleOwner, Observer {
            // hacemos update para actualizar nuestro adaptador
            it?.let {
                adapter.update(it)


            }
        })

        return _binding.root
    }


    private fun clearDatabase() {
        viewModelConsumo.clearDatabase()
    }

}