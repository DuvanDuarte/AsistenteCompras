package com.example.asistentecompras

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.viewpager2.widget.ViewPager2
import android.widget.Button

class MainTabsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tabs)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val btnSalir = findViewById<Button>(R.id.btnSalir)


        viewPager.adapter = TabsPagerAdapter(this)

        val titulos = listOf("Descuentos", "Dividir cuenta", "Compra a cuotas")
        val iconos = listOf(
            R.drawable.ic_tab_descuentos,
            R.drawable.ic_tab_dividir,
            R.drawable.ic_tab_cuotas
        )

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = titulos[position]
            tab.setIcon(iconos[position])
        }.attach()

        btnSalir.setOnClickListener {
            UsuarioSesion.nombre = ""
            UsuarioSesion.correo = ""
            UsuarioSesion.moneda = "COP"

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}