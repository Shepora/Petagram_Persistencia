package com.example.petagrampersistencia

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListaMascotasFragment : Fragment(R.layout.fragment_lista_mascotas) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mascotas = arrayListOf(
            Mascota(1, "Catty", R.drawable.dog_1, 5),
            Mascota(2, "Ronny", R.drawable.dog_2, 3),
            Mascota(3, "Luna", R.drawable.dog_3, 4),
            Mascota(4, "Rocky", R.drawable.dog_4, 2),
            Mascota(5, "Coco", R.drawable.dog_5, 1)
        )

        val guardadas = BaseDatos(requireContext()).use { it.ultimasCinco() }
        for (guardada in guardadas) {
            mascotas.find { it.id == guardada.id }?.let {
                it.rating = guardada.rating
                it.fechaRating = guardada.fechaRating
            }
        }

        val lista = view.findViewById<RecyclerView>(R.id.rvMascotas)
        lista.layoutManager = LinearLayoutManager(requireContext())
        lista.adapter = MascotaAdaptador(mascotas)
    }
}
