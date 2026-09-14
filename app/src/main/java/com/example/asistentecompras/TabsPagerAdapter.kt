package com.example.asistentecompras

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabsPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): androidx.fragment.app.Fragment {
        return when (position) {
            0 -> DescuentosFragment()
            1 -> DividirCuentaFragment()
            2 -> CompraCuotasFragment()
            else -> throw IllegalStateException("Posición inválida: $position")
        }
    }
}