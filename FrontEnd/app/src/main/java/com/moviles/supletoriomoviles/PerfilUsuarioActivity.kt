package com.moviles.supletoriomoviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_perfil_usuario.*

class PerfilUsuarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_usuario)
        val idUsu = intent.getIntExtra("Usuario",0)
        Log.i("pedro", "1"+idUsu.toString())
        val usuario = DatabaseUsuario.getListId(idUsu)
        Log.i("pedro", "2"+usuario)
        txt_nombre_pu?.text = "Nombre: "+usuario.get(0)?.nombre
        txt_apellifo_pu?.text ="Apellido: "+usuario.get(0)?.apellido
        txt_correo_pu?.text= "Email: "+usuario.get(0)?.email
        txt_fecha_pu?.text="Fecha de Nacimiento: "+usuario.get(0)?.fechaNacimiento
        txt_experiencia_pu?.text="Total de Experiencia: "+usuario.get(0)?.total_experiencia
        txt_oro_pu?.text="Total de Oro: "+usuario.get(0)?.total_oro
        btn_regresar_menu.setOnClickListener {
            this.regresar()
        }
    }

    fun regresar(){
        finish()
        val intent = Intent(this,MenuJuegoActivity::class.java)
        startActivity(intent)
    }
}
