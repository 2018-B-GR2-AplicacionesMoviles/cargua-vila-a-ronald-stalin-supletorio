package com.moviles.supletoriomoviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tapadoo.alerter.Alerter
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_crear_usuario.*

class CrearUsuarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_usuario)

        btn_cancelar.setOnClickListener {
            this.irAMain()
        }

        btn_guardar_u.setOnClickListener {
            this.guardarDatos()
        }
    }

    fun irAMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun guardarDatos(){
        if (txt_nombre_u.text.toString().isEmpty()||
                txt_apellido_u.text.toString().isEmpty()||
                txt_fecha_naciemiento_u.text.toString().isEmpty()||
                txt_email_u.text.toString().isEmpty()||
                txt_password_u.text.toString().isEmpty()){
            Alerter.create(this).setTitle("Campos Vacios")
                .setText("Completa la informacion de todos los campos")
                .setBackgroundColorRes(R.color.error_color_material_dark)
                .enableSwipeToDismiss()
                .show()
        }else{
            var nombreUsuario = txt_nombre_u.text.toString()
            var apellidoUsuario = txt_apellido_u.text.toString()
            var fechaNacimeintoUsuario = txt_fecha_naciemiento_u.text.toString()
            var emailUsuario = txt_email_u.text.toString()
            var passwordUsuario = txt_password_u.text.toString()

            var usuario = Usuario(0,nombreUsuario,apellidoUsuario,fechaNacimeintoUsuario,emailUsuario,passwordUsuario,0,0)
            DatabaseUsuario.insertarUsuario(usuario)

            Toasty.success(this, "Datos registrados", Toast.LENGTH_LONG, true).show()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}
