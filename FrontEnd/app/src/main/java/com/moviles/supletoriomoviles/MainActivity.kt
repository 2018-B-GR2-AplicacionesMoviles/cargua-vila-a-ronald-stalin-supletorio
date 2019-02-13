package com.moviles.supletoriomoviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tapadoo.alerter.Alerter
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var usuarios : ArrayList<Usuario>
    var estadoIngresoSistema = 0
    lateinit var valorIdUser:String
    lateinit var usuarioActual:String
    var usuario : Usuario? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_crear_usuario.setOnClickListener {
            this.irACrearUsuario()
        }
        btn_login.setOnClickListener {
            this.login()
        }

    }
    fun irACrearUsuario(){
        val intent = Intent(this, CrearUsuarioActivity::class.java)
        startActivity(intent)
    }

    fun irAMenuPrincipal(){
        val intent = Intent(this, MenuPrincipalActivity::class.java)
        startActivity(intent)
    }

    fun login(){
        var emailUsuario = txt_email.text.toString()
        var passwordUsuario = txt_password.text.toString()
        Log.i("datosUsu", emailUsuario)
        Log.i("datosUsu",passwordUsuario)
        usuarios = DatabaseUsuario.getList()

        for (datos in usuarios){
            Log.i("http-1", "DatosMain: ${datos}")
            if (datos.email.equals(emailUsuario,true)&&datos.password.equals(passwordUsuario,true)){
                estadoIngresoSistema = 1
                usuarioActual = datos.nombre
                valorIdUser = datos.idUser.toString()
            }
        }

        if (estadoIngresoSistema == 1){
            Toasty.success(this,
                "Bienvenido al Sistema: ${usuarioActual}",
                Toast.LENGTH_LONG,true).show()
            this.irAMenuPrincipal()
        }else{
            Alerter.create(this)
                .setTitle("Datos Incorrectos")
                .setText("Verifica tus datos e intenta de nuevo")
                .setBackgroundColorRes(R.color.error_color_material_dark)
                .enableSwipeToDismiss()
                .show()
            txt_email.setText("")
            txt_password.setText("")
        }
    }

}
