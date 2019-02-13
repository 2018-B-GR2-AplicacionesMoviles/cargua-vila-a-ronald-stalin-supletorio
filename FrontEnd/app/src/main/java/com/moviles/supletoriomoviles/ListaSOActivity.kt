package com.moviles.supletoriomoviles

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import es.dmoral.toasty.Toasty

class ListaSOActivity : AppCompatActivity() {

    var so : ArrayList<SistemaOperativo>?=null
    var lista: RecyclerView? = null
    var layoutManager : RecyclerView.LayoutManager? = null
    var adaptador : AdaptadorListaSO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_so)


        so = DatabaseSO.getList()
        Log.i("bddd", "${so}")
        lista = findViewById(R.id.reciclerListaSO)
        layoutManager = LinearLayoutManager(this)
        adaptador = AdaptadorListaSO(so!!,object : ClickListener{
            override fun onClick(vista: View, posicion: Int) {
                Toast.makeText(applicationContext, so?.get(posicion)?.nombre, Toast.LENGTH_SHORT).show()
            }
        })
        lista?.layoutManager = layoutManager
        lista?.adapter= adaptador
    }
}
