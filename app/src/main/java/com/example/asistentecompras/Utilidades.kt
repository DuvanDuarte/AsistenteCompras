package com.example.asistentecompras

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

fun obtenerFormatoMoneda(): NumberFormat {
    val locale = when (UsuarioSesion.moneda) {
        "USD" -> Locale.US
        "EUR" -> Locale.GERMANY
        else -> Locale("es", "CO")
    }

    val formato = NumberFormat.getCurrencyInstance(locale)

    formato.currency = Currency.getInstance(UsuarioSesion.moneda)

    return formato
}