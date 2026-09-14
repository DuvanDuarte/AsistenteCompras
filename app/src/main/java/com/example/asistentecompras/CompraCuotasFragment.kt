package com.example.asistentecompras

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.math.pow

class CompraCuotasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_compra_cuotas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etValorProducto = view.findViewById<EditText>(R.id.etValorProducto)
        val etNumCuotas = view.findViewById<EditText>(R.id.etNumCuotas)
        val etTasaInteres = view.findViewById<EditText>(R.id.etTasaInteres)
        val btnCalcular = view.findViewById<Button>(R.id.btnCalcularCuotas)
        val tvValorOriginal = view.findViewById<TextView>(R.id.tvValorOriginal)
        val tvValorCuota = view.findViewById<TextView>(R.id.tvValorCuota)

        val formatoMoneda = obtenerFormatoMoneda()

        btnCalcular.setOnClickListener {
            val valorProducto = etValorProducto.text.toString().trim().toDoubleOrNull()
            val numCuotas = etNumCuotas.text.toString().trim().toIntOrNull()
            val tasaInteres = etTasaInteres.text.toString().trim().toDoubleOrNull()


            if (valorProducto == null || valorProducto <= 0) {
                etValorProducto.error = "El valor del producto debe ser mayor que 0"
                return@setOnClickListener
            }

            if (numCuotas == null || numCuotas <= 0) {
                etNumCuotas.error = "El número de cuotas debe ser mayor que 0"
                return@setOnClickListener
            }

            if (tasaInteres == null || tasaInteres < 0) {
                etTasaInteres.error = "La tasa de interés no puede ser negativa"
                return@setOnClickListener
            }

            val valorCuota: Double

            if (tasaInteres == 0.0) {
                valorCuota = valorProducto / numCuotas
            } else {
                val i = tasaInteres / 100
                val factor = (1 + i).pow(numCuotas)
                valorCuota = valorProducto * (i * factor) / (factor - 1)
            }

            tvValorOriginal.text = "Valor original del producto: ${formatoMoneda.format(valorProducto)}"
            tvValorCuota.text = "Valor aproximado de cada cuota: ${formatoMoneda.format(valorCuota)}"

            etValorProducto.text.clear()
            etNumCuotas.text.clear()
            etTasaInteres.text.clear()
        }
    }
}