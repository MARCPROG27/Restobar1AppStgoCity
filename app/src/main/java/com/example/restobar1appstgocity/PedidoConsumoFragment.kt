package com.example.restobar1appstgocity

import android.R
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.restobar1appstgocity.Model.ConsumosEntity
import com.example.restobar1appstgocity.ViewModel.ConsumosViewModel
import com.example.restobar1appstgocity.databinding.FragmentPedidoConsumoBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PedidoConsumoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PedidoConsumoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var _binding: FragmentPedidoConsumoBinding

    // private val viewModel: ProductosViewModel by activityViewModels()
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

            idMars = it.getString("id", "")
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPedidoConsumoBinding.inflate(inflater, container, false)



        _binding.txtcantidadp.addTextChangedListener(object : TextWatcher {

            //EN ESTE EVENTO EL USUARIO A MEDIDA QUE ESCRIBA EL PRODUCTO, PRECIO Y CANTIDAD, APARECERÁ TAMBIEN EL VALOR TOTAL ACTUAL

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {

                //EVENTO DONDE REALIZA EL TIPEO DE LOS ITEM SOLICITADOS Y VALOR TOTAL
                // EN CASO QUE EL VALOR EN CANTIDAD ES NULO  APARECERÁ UN CUADRO DE AVISO ALERTDIALOG QUE AVISARÁ
                val resultado: Int
                val cantidad: Int

                val precioitem: Int

                if (binding.txtcantidadp.text.isEmpty()) {

                    val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                    builder.setMessage("RECUERDE ,INGRESAR UNA CANTIDAD MAYOR QUE CERO EN ITEM CANTIDAD")
                        .setTitle("AVISO")
                        .setPositiveButton(R.string.ok, null)
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                } else {
                    //AQUI SE HACE EL PROCESO DE MULTIPLICAR CANTIDAD POR PRECIO Y ASIGNACION DE VARIABLES

                    cantidad = binding.txtcantidadp.text.toString().toInt()
                    precioitem = binding.txtpreciopr.text.toString().toInt()
                    resultado = cantidad * precioitem

                    binding.txtvalortotal.setText("$ $resultado")
                }


            }


        })

        binding.btnguardartotal.setOnClickListener {
            // MÉTODO GUARDA LOS PRODUCTOS QUE HAN PEDIDO PARA CONSUMIR Y APARECEN EN BASE DE DATOS ROOM
            val vtotal: Int
            val nomproducto = binding.txtcantidadpr.text.toString()
            //extrae el signo monetario peso de la variable para guardar el resto de la cantidad en la base de datos
            val valortotal = binding.txtvalortotal.text.toString().substring(2, binding.txtvalortotal.text.length).trim()
            val cantidadconsumo = binding.txtcantidadp.toString()

            vtotal = valortotal.toInt()


            // SE VALIDAD ALGUNOS CAMPOS
            if (cantidadconsumo == null) {
                Toast.makeText(
                    context,
                    " Debe añadir cantidad de productos pedidos para consumo",
                    Toast.LENGTH_LONG
                ).show()

            }

            // SI NO HAY NADA CREA UNA NUEVA ACTIVIDAD
            // SI HAY ALGO EN LA BASE DE DATOS LA ACTUALIZA
            if (idPedido == 0) {
                val newPedido = ConsumosEntity(
                    nomproducto = nomproducto,
                    valortotal = vtotal
                )
                viewModelConsumo.insertConsumo(newPedido)
                Toast.makeText(
                    context,
                    " PEDIDO DE CONSUMO GUARDADO CORRECTAMENTE",
                    Toast.LENGTH_LONG
                ).show()

            }

        }

        binding.btnnueboprod.setOnClickListener {
            ingresarnuevoProducto()
        }

        binding.floatingActionButton.setOnClickListener {
            //FRAGMENTO NAVEGA A FRAGMENT DONDE ESTA EL RECYCLERVIEW CON LOS DATOS ALMACENADOS Y ACTUALIZADOS PRODUCTO Y CONSUMO PAGADO POR PRODUCTO
            findNavController().navigate(com.example.restobar1appstgocity.R.id.action_pedidoConsumoFragment_to_listadoConsumosFragment)


        }

        return _binding.root


    }

    private fun ingresarnuevoProducto() {
        //LIMPIA LOS CAMPOS PARA INGRESAR UN NUEVO PRODUCTO Y AVISA QUE CAMPO CANTIDAD NO DEBE ESTAR EN BLANCO O NULO
        binding.txtcantidadpr.setText("")
        binding.txtcantidadp.setText("")
        binding.txtpreciopr.setText("")
        binding.txtvalortotal.setText("")
        binding.txtcantidadpr.requestFocus()


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}