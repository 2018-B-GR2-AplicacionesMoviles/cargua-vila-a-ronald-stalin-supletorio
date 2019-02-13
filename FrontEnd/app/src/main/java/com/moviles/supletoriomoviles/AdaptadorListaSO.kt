package com.moviles.supletoriomoviles

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class AdaptadorListaSO(private val listaSO : ArrayList<SistemaOperativo>,
                       private  val contexto : ListaSOActivity,
                       private  val recyclerView: RecyclerView): RecyclerView.Adapter<AdaptadorListaSO.MyViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(p0.context)
            .inflate(R.layout.lista_sistemas_operativos,p0,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return listaSO.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        val sistemaOperativo = listaSO[p1]

        p0.nombreSO.setText(sistemaOperativo.nombre)
        p0.versioSO.setText(sistemaOperativo.versionApi)
        p0.fechaLanzamientoSO.setText(sistemaOperativo.fechaLanzamiento)
        p0.pesoGigasSO.setText(sistemaOperativo.pesoGigas.toString())
        p0.intsaladoSO.setText(sistemaOperativo.instalado.toString())

    }

    inner class MyViewHolder (view: View): RecyclerView.ViewHolder(view) {
        var nombreSO: TextView
        var versioSO: TextView
        var fechaLanzamientoSO: TextView
        var pesoGigasSO: TextView
        var intsaladoSO: TextView
        init {
            nombreSO = view.findViewById(R.id.nombre_lista_so)
            versioSO = view.findViewById(R.id.version_lista_so)
            fechaLanzamientoSO = view.findViewById(R.id.fecha_lanzamiento_lista_so)
            pesoGigasSO = view.findViewById(R.id.peso_lista_so)
            intsaladoSO = view.findViewById(R.id.instalado_lista_so)
        }


    }

}