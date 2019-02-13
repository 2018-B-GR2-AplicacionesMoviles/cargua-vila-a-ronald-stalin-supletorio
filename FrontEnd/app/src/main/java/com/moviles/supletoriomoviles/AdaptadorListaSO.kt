package com.moviles.supletoriomoviles

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.lista_sistemas_operativos.view.*

class AdaptadorListaSO(private val listaSO : ArrayList<SistemaOperativo>, var clickListener : ClickListener
                       ): RecyclerView.Adapter<AdaptadorListaSO.MyViewHolder>(){
    var items : ArrayList<SistemaOperativo>?= null
    var viewHolder: MyViewHolder?= null
    init {
        this.items = listaSO
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorListaSO.MyViewHolder {
        val itemView = LayoutInflater
            .from(parent?.context)
            .inflate(R.layout.lista_sistemas_operativos,parent,false)
            viewHolder = MyViewHolder(itemView,clickListener)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
       return this.items?.count()!!
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items?.get(position)

        holder.nombreSO?.text = "Nombre: "+item?.nombre
        holder.versioSO?.text = "Versión API: "+item?.versionApi.toString()
        holder.fechaLanzamientoSO?.text = "Fecha Lanzamiento: "+item?.fechaLanzamiento
        holder.pesoGigasSO?.text = "Peso en GB: "+item?.pesoGigas.toString()
        if (item?.instalado == true){
            holder.intsaladoSO?.text = "Instalado: Si"
        }else{
            holder.intsaladoSO?.text = "Instalado: No"
        }
    }

    class MyViewHolder (view: View, listener: ClickListener): RecyclerView.ViewHolder(view),View.OnClickListener {


        var vista = view
        var nombreSO: TextView?=null
        var versioSO: TextView?=null
        var fechaLanzamientoSO: TextView?=null
        var pesoGigasSO: TextView?=null
        var intsaladoSO: TextView?=null
        var listener : ClickListener?= null
        init {
            nombreSO = vista.nombre_lista_so
            versioSO = vista.version_lista_so
            fechaLanzamientoSO = vista.fecha_lanzamiento_lista_so
            pesoGigasSO = vista.peso_lista_so
            intsaladoSO = vista.instalado_lista_so
            this.listener = listener
            vista.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!,adapterPosition)
        }
    }

}