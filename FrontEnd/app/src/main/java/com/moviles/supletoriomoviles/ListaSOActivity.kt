package com.moviles.supletoriomoviles

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_lista_so.*

class ListaSOActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_so)

        val layoutManager = LinearLayoutManager(this)
        val rv = reciclerListaSO
        val bdd = DatabaseSO.getList()
        Log.i("bddd", "${bdd}")
        val adaptador = AdaptadorListaSO( bdd, this, rv)

        reciclerListaSO.layoutManager = layoutManager
        reciclerListaSO.itemAnimator = DefaultItemAnimator()
        reciclerListaSO.adapter= adaptador

        adaptador.notifyDataSetChanged()
    }
}
