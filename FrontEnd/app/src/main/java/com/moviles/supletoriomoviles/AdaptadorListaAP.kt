package com.moviles.supletoriomoviles

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.lista_aplicaciones.view.*


class AdaptadorListaAP(private val listaAP : ArrayList<Aplicacion>, var clickListener : ClickListener
): RecyclerView.Adapter<AdaptadorListaAP.MyViewHolder>(){
    var items : ArrayList<Aplicacion>?= null
    var viewHolder: MyViewHolder?= null
    init {
        this.items = listaAP
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorListaAP.MyViewHolder {
        val itemView = LayoutInflater
            .from(parent?.context)
            .inflate(R.layout.lista_aplicaciones,parent,false)
        viewHolder = MyViewHolder(itemView,clickListener)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return this.items?.count()!!
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items?.get(position)

        holder.pesoGigasAP?.text = "Peso en Gigas: "+item?.pesoGigas
        holder.versionesAP?.text = "Versión: "+item?.versiones
        holder.nombresAP?.text = "Nombre: "+item?.nombres
        holder.fechaLanzamientoAP?.text = "Fecha Lanzamiento: "+item?.fechaLanzamiento
        holder.urlDescargaAP?.text="URL Descarga: "+item?.urlDescarga
        holder.costoAP?.text="Costo: "+item?.costo
    }

    class MyViewHolder (view: View, listener: ClickListener): RecyclerView.ViewHolder(view), View.OnClickListener {


        var vista = view
        var pesoGigasAP: TextView?=null
        var versionesAP: TextView?=null
        var nombresAP: TextView?=null
        var fechaLanzamientoAP: TextView?=null
        var urlDescargaAP: TextView?=null
        var costoAP: TextView?=null
        var listener : ClickListener?= null
        init {
            pesoGigasAP = vista.peso_lista_so
            versionesAP = vista.version_lista_so
            nombresAP = vista.nombre_lista_ap_lista_ap
            fechaLanzamientoAP = vista.fecha_lanzamiento_lista_so
            urlDescargaAP = vista.url_lista_ap
            costoAP = vista.costo_lista_ap
            this.listener = listener
            vista.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!,adapterPosition)
        }
    }


}