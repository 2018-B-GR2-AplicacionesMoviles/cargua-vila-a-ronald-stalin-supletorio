package com.moviles.supletoriomoviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu_juego.*

class MenuJuegoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_juego)

        btn_registrar.setOnClickListener {
            this.irARegistrarAplicaciones()
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
            this.irAVerPerfil()
        }
    }
    fun irARegistrarAplicaciones(){
        val inten = Intent(this, RegistrarAPPActivity::class.java)
        startActivity(inten)
    }

    fun irAVerAplicaciones(){
        val inten = Intent(this, VerAplicacionesActivity::class.java)
        startActivity(inten)
    }

    fun irARecolectarOBatalla(){
        val inten = Intent(this, RecolectarBatallaActivity::class.java)
        startActivity(inten)
    }

    fun irAInfoBatalla(){
        val inten = Intent(this, InfoBatallasActivity::class.java)
        startActivity(inten)
    }

    fun irAVerPerfil(){
        val inten = Intent(this, PerfilUsuarioActivity::class.java)
        startActivity(inten)
    }
}
