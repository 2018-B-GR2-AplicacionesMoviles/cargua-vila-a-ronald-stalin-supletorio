package com.moviles.supletoriomoviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_menu_juego.*

class MenuJuegoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_juego)

        val idUsua = intent.getIntExtra("IdUsuario",0)

        btn_registrar.setOnClickListener {
            this.irARegistrarAplicaciones(idUsua)
        }

        btn_ver_app.setOnClickListener {
            this.irAVerAplicaciones()
        }
        btn_batalla.setOnClickListener {
            this.irARecolectarOBatalla()
        }
        btn_info_batalla.setOnClickListener {
            this.irAInfoBatalla()
        }
        btn_perfil.setOnClickListener {
            this.irAVerPerfil(idUsua)
        }
    }
    fun irARegistrarAplicaciones(idUsuario: Int){

        val inten = Intent(this, RegistrarAPPActivity::class.java)
        inten.putExtra("Usuario",idUsuario)
        startActivity(inten)
    }

    fun irAVerAplicaciones(){
        val inten = Intent(this, VerAplicacionesActivity::class.java)
        startActivity(inten)
    }

    fun irARecolectarOBatalla(){
        val inten = Intent(this, BatallaMapsActivity::class.java)
        startActivity(inten)
    }

    fun irAInfoBatalla(){
        val inten = Intent(this, InfoBatallasActivity::class.java)
        startActivity(inten)
    }

    fun irAVerPerfil(idUsuario: Int){
        val inten = Intent(this, PerfilUsuarioActivity::class.java)
        inten.putExtra("Usuario",idUsuario)
        startActivity(inten)
    }
}
