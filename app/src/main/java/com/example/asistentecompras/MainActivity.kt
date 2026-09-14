package com.example.asistentecompras
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import android.util.Patterns
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val spinnerMoneda = findViewById<Spinner>(R.id.spinnerMoneda)
        val btnContinuar = findViewById<Button>(R.id.btnContinuar)

        val monedas = listOf("COP", "USD", "EUR")
        spinnerMoneda.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, monedas)

        btnContinuar.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val correo = etCorreo.text.toString().trim()


            if (nombre.isEmpty() && correo.isEmpty()) {
                Toast.makeText(this, "Los campos están vacíos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (nombre.isEmpty()) {
                etNombre.error = "Ingresa tu nombre"
                return@setOnClickListener
            }

            if (correo.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                etCorreo.error = "Ingresa un correo válido"
                return@setOnClickListener
            }


            UsuarioSesion.nombre = nombre
            UsuarioSesion.correo = correo
            UsuarioSesion.moneda = spinnerMoneda.selectedItem.toString()

            val intent = Intent(this, MainTabsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}