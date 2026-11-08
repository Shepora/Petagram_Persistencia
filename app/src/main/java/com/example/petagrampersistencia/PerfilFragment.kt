package com.example.petagrampersistencia

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PerfilFragment : Fragment(R.layout.fragment_perfil) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ratings = listOf(5, 0, 3, 10, 2, 3, 7, 1, 4, 6, 8, 2)
        val fotos = ratings.map { Foto(R.drawable.dog_2, it) }

        val grid = view.findViewById<RecyclerView>(R.id.rvFotos)
        grid.layoutManager = GridLayoutManager(requireContext(), 3)
        grid.adapter = FotoAdaptador(fotos)
    }
}
