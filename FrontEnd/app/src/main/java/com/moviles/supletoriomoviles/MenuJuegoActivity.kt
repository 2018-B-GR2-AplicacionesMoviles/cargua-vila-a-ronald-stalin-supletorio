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
        Log.i("id", idUsua.toString())
        btn_registrar.setOnClickListener {
            this.irARegistrarAplicaciones(idUsua)
        }

        btn_ver_app.setOnClickListener {
            this.irAVerAplicaciones(idUsua)
        }
        btn_batalla.setOnClickListener {
            this.irARecolectarOBatalla(idUsua)
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

    fun irAVerAplicaciones(idUsuario: Int){
        val inten = Intent(this, VerAplicacionesActivity::class.java)
        inten.putExtra("Usuario",idUsuario)
        startActivity(inten)
    }

    fun irARecolectarOBatalla(idUsuario: Int){
        val inten = Intent(this, RecolectarBatallaActivity::class.java)
        inten.putExtra("Usuario",idUsuario)
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
