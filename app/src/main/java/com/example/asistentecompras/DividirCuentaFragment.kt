package com.example.asistentecompras

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class DividirCuentaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dividir_cuenta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etValorCuenta = view.findViewById<EditText>(R.id.etValorCuenta)
        val etNumPersonas = view.findViewById<EditText>(R.id.etNumPersonas)
        val etPorcentajePropina = view.findViewById<EditText>(R.id.etPorcentajePropina)
        val btnCalcular = view.findViewById<Button>(R.id.btnCalcularDivision)
        val tvPropina = view.findViewById<TextView>(R.id.tvPropina)
        val tvTotalConPropina = view.findViewById<TextView>(R.id.tvTotalConPropina)
        val tvPagoPorPersona = view.findViewById<TextView>(R.id.tvPagoPorPersona)

        val formatoMoneda = obtenerFormatoMoneda()

        btnCalcular.setOnClickListener {
            val valorCuenta = etValorCuenta.text.toString().trim().toDoubleOrNull()
            val numPersonas = etNumPersonas.text.toString().trim().toIntOrNull()
            val porcentajePropina = etPorcentajePropina.text.toString().trim().toDoubleOrNull()

            if (valorCuenta == null || valorCuenta <= 0) {
                etValorCuenta.error = "El valor del producto debe ser mayor que 0"
                return@setOnClickListener
            }

            if (numPersonas == null || numPersonas <= 0) {
                etNumPersonas.error = "El número de personas debe ser mayor que 0"
                return@setOnClickListener
            }

            if (porcentajePropina == null || porcentajePropina < 0) {
                etPorcentajePropina.error = "Ingresa un porcentaje válido"
                return@setOnClickListener
            }

            val valorPropina = valorCuenta * (porcentajePropina / 100)
            val totalConPropina = valorCuenta + valorPropina
            val pagoPorPersona = totalConPropina / numPersonas

            tvPropina.text = "Propina: ${formatoMoneda.format(valorPropina)}"
            tvTotalConPropina.text = "Total: ${formatoMoneda.format(totalConPropina)}"
            tvPagoPorPersona.text = "Cada persona paga: ${formatoMoneda.format(pagoPorPersona)}"

            etValorCuenta.text.clear()
            etNumPersonas.text.clear()
            etPorcentajePropina.text.clear()
        }
    }
}