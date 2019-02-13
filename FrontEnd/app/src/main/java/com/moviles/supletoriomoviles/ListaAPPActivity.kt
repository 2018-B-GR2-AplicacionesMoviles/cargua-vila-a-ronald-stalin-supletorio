package com.moviles.supletoriomoviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast

class ListaAPPActivity : AppCompatActivity() {

    var so : ArrayList<Aplicacion>?=null
    var lista: RecyclerView? = null
    var layoutManager : RecyclerView.LayoutManager? = null
    var adaptador : AdaptadorListaAP? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_app)
        val listaSO = this
        so = DatabaseAPP.getList()
        Log.i("bddd", "${so}")
        lista = findViewById(R.id.reciclerListaAP)
        layoutManager = LinearLayoutManager(this)
        adaptador = AdaptadorListaAP(so!!,object : ClickListener{
            override fun onClick(vista: View, posicion: Int) {
                Toast.makeText(applicationContext, so?.get(posicion)?.nombres, Toast.LENGTH_SHORT).show()
            }
        })

        lista?.layoutManager = layoutManager
        lista?.adapter= adaptador

        adaptador?.notifyDataSetChanged()
    }

    fun irACrearAplicacion (){
        val intent = Intent(this, CrearAplicacionActivity::class.java)
        startActivity(intent)
    }
}
