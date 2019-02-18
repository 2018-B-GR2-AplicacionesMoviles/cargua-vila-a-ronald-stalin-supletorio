package com.moviles.supletoriomoviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.registrar_hijos.*
import java.util.ArrayList

class VerAplicacionesActivity : AppCompatActivity() {

    var so : ArrayList<AppPorUsuario2>?=null
    var lista: RecyclerView? = null
    var layoutManager : RecyclerView.LayoutManager? = null
    var adaptador : AdaptadorVerAplicaciones? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_aplicaciones)

        var listaVer = this
        so = DatabaseAPPorUsuario.getList()

        lista = findViewById(R.id.reciclerListaRegistrar)
        layoutManager = LinearLayoutManager(this)
        adaptador = AdaptadorVerAplicaciones(so!!, object : ClickListener{
            override fun onClick(vista: View, posicion: Int) {

                Toast.makeText(applicationContext, so?.get(posicion)?.nombreApp, Toast.LENGTH_SHORT).show()
            }
        })
        lista?.layoutManager = layoutManager
        lista?.adapter= adaptador
        adaptador?.notifyDataSetChanged()
    }

}
