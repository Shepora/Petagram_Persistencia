package com.example.petagrampersistencia

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FotoAdaptador(private val fotos: List<Foto>) :
    RecyclerView.Adapter<FotoAdaptador.FotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FotoViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_foto, parent, false)
        return FotoViewHolder(vista)
    }

    override fun onBindViewHolder(holder: FotoViewHolder, position: Int) {
        val foto = fotos[position]
        holder.imgFoto.setImageResource(foto.imagen)
        holder.tvRating.text = foto.rating.toString()
    }

    override fun getItemCount(): Int = fotos.size

    class FotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFoto: ImageView = itemView.findViewById(R.id.imgFoto)
        val tvRating: TextView = itemView.findViewById(R.id.tvRating)
    }
}
