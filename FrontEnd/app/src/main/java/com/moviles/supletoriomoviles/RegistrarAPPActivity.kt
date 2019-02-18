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
import kotlinx.android.synthetic.main.registrar_hijos.*
import java.util.*


class RegistrarAPPActivity : AppCompatActivity() {

    var so : ArrayList<Aplicacion>?=null
    var id: Int?= 0
    var apBuscar : ArrayList<Aplicacion>?=null
    var lista: RecyclerView? = null
    var layoutManager : RecyclerView.LayoutManager? = null
    var adaptador : AdaptadorRegistarAP? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_app)

        var idUsuario = intent.getIntExtra("Usuario",0)
        Log.i("hola","Menu Juego212${idUsuario}")

        so = DatabaseAPP.getList()
        id= DatabaseAPPorUsuario.getId()

        lista = findViewById(R.id.reciclerListaRegistrar)
        layoutManager = LinearLayoutManager(this)
        adaptador = AdaptadorRegistarAP(so!!, object : ClickListener{
            override fun onClick(vista: View, posicion: Int) {
                btn_aniadir_hijo.setOnClickListener {

                    Log.i("hola","${idUsuario}, ${so?.get(posicion)?.idAP!!}")
                    agregarAPP(id!!,idUsuario ,so?.get(posicion)?.idAP!!)
                    var app = Aplicacion(so?.get(posicion)?.idAP!!,
                        so?.get(posicion)?.pesoGigas!!,
                        so?.get(posicion)?.versiones!!,
                        so?.get(posicion)?.nombres!!,
                        so?.get(posicion)?.urlDescarga!!,
                        so?.get(posicion)?.fechaLanzamiento!!,
                        so?.get(posicion)?.costo!!,
                        "personaje",
                        so?.get(posicion)?.soId!!)
                    DatabaseAPP.editarSO(app)
                    regresar()
                }
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
    fun regresar(){
        val intent = Intent(this,MenuJuegoActivity::class.java)
        startActivity(intent)
    }
    fun agregarAPP(id: Int,idUsuario: Int, idApp : Int){
        var idd = id + 1
        Log.i("http-4",id.toString())
        var appPorUsuario = AppPorUsuario(idd,idApp,idUsuario,0.0,0,0)
        DatabaseAPPorUsuario.insertarSO(appPorUsuario)
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
