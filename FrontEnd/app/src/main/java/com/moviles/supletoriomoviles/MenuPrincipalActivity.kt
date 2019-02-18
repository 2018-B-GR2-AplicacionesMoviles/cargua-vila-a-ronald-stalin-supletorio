package com.moviles.supletoriomoviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_menu_principal.*

class MenuPrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
        val idUsua = intent.getIntExtra("IdUsuario",0)
        btn_crear_so.setOnClickListener {
            this.irACrearSO()
        }
        btn_lista_so.setOnClickListener {
            this.irAListaSO()
        }
        btn_jugar.setOnClickListener {
            this.irAJugar(idUsua)
        }
    }
    fun irAJugar( idUsuario: Int){

        val intent = Intent(this, MenuJuegoActivity::class.java)
        intent.putExtra("IdUsuario",idUsuario)
        startActivity(intent)
    }
    fun irACrearSO(){
        val intent = Intent(this,CrearSOActivity::class.java)
        startActivity(intent)
    }
    fun irAListaSO(){
        val intent = Intent(this, ListaSOActivity::class.java)
        startActivity(intent)
    }
}
