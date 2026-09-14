package com.example.asistentecompras

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.NumberFormat
import java.util.Locale

class DescuentosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_descuentos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etPrecioOriginal = view.findViewById<EditText>(R.id.etPrecioOriginal)
        val etPorcentajeDescuento = view.findViewById<EditText>(R.id.etPorcentajeDescuento)
        val btnCalcular = view.findViewById<Button>(R.id.btnCalcularDescuento)
        val tvValorDescuento = view.findViewById<TextView>(R.id.tvValorDescuento)
        val tvTotalPagar = view.findViewById<TextView>(R.id.tvTotalPagar)

        val formatoMoneda = obtenerFormatoMoneda()

        btnCalcular.setOnClickListener {
            val precioTexto = etPrecioOriginal.text.toString().trim()
            val porcentajeTexto = etPorcentajeDescuento.text.toString().trim()

            val precio = precioTexto.toDoubleOrNull()
            val porcentaje = porcentajeTexto.toDoubleOrNull()

            if (precio == null || precio < 0) {
                etPrecioOriginal.error = "Ingresa un precio válido (mayor o igual a 0)"
                return@setOnClickListener
            }

            if (porcentaje == null || porcentaje < 0 || porcentaje > 100) {
                etPorcentajeDescuento.error = "El descuento debe estar entre 0 y 100"
                return@setOnClickListener
            }

            val valorDescuento = precio * (porcentaje / 100)
            val totalPagar = precio - valorDescuento

            tvValorDescuento.text = "Descuento aplicado: ${formatoMoneda.format(valorDescuento)}"
            tvTotalPagar.text = "Total a pagar: ${formatoMoneda.format(totalPagar)}"

            etPrecioOriginal.text.clear()
            etPorcentajeDescuento.text.clear()
        }
    }
}