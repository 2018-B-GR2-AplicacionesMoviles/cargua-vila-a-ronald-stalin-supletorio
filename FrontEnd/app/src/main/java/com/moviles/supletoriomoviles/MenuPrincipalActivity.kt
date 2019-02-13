package com.moviles.supletoriomoviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu_principal.*

class MenuPrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)
        btn_crear_so.setOnClickListener {
            this.irACrearSO()
        }
        btn_lista_so.setOnClickListener {
            this.irAListaSO()
        }
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
