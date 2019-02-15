package com.moviles.supletoriomoviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View

import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registrar_app.*


class RegistrarAPPActivity : AppCompatActivity() {

    var so : ArrayList<Aplicacion>?=null
    var apBuscar : ArrayList<Aplicacion>?=null
    var lista: RecyclerView? = null
    var layoutManager : RecyclerView.LayoutManager? = null
    var adaptador : AdaptadorRegistarAP? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_app)

        so = DatabaseAPP.getList()

        Log.i("bddd", "${so}")
        lista = findViewById(R.id.reciclerListaRegistrar)
        layoutManager = LinearLayoutManager(this)
        adaptador = AdaptadorRegistarAP(so!!, object : ClickListener{
            override fun onClick(vista: View, posicion: Int) {
                Toast.makeText(applicationContext, so?.get(posicion)?.nombres, Toast.LENGTH_SHORT).show()
            }
        })
        lista?.layoutManager = layoutManager
        lista?.adapter= adaptador
        adaptador?.notifyDataSetChanged()

        btn_buscar.setOnClickListener {
            this.buscar()
        }
    }

    fun buscar(){
        var nombre = txt_buscar.text.toString()
        apBuscar = DatabaseAPP.buscar(nombre)
        lista = findViewById(R.id.reciclerListaRegistrar)
        layoutManager = LinearLayoutManager(this)
        adaptador = AdaptadorRegistarAP(so!!, object : ClickListener{
            override fun onClick(vista: View, posicion: Int) {
                Toast.makeText(applicationContext, so?.get(posicion)?.nombres, Toast.LENGTH_SHORT).show()
            }
        })
        lista?.layoutManager = layoutManager
        lista?.adapter= adaptador
        adaptador?.notifyDataSetChanged()

    }
}
