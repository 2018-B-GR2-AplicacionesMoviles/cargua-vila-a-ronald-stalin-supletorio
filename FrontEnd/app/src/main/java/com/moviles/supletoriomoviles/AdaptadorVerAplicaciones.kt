package com.moviles.supletoriomoviles

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.ver_hijos.view.*

class AdaptadorVerAplicaciones(private val listaAP : ArrayList<AppPorUsuario2>, var clickListener : ClickListener
): RecyclerView.Adapter<AdaptadorVerAplicaciones.MyViewHolder>(){
    var items : ArrayList<AppPorUsuario2>?= null
    var viewHolder: MyViewHolder?= null
    init {
        this.items = listaAP
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorVerAplicaciones.MyViewHolder {
        val itemView = LayoutInflater
            .from(parent?.context)
            .inflate(R.layout.ver_hijos,parent,false)
        viewHolder = MyViewHolder(itemView,clickListener)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return this.items?.count()!!
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items?.get(position)


        holder.verAplicaciones?.text = "Nombre App: "+item?.nombreApp+
                " - Experiencia Total: "+item?.experienciaApp+
                " - Numero de Batallas: "+item?.numBatallas+
                " - Numero de Recolecciones: "+ item?.numRecolectas

    }

    class MyViewHolder (view: View, listener: ClickListener): RecyclerView.ViewHolder(view), View.OnClickListener {
        var vista = view
        var verAplicaciones: TextView?=null
        var listener : ClickListener?= null
        init {

            verAplicaciones = vista.txt_ver_hijos
            this.listener = listener
            vista.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!,adapterPosition)
        }
    }




}