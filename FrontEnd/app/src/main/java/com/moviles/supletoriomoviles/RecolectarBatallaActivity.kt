package com.moviles.supletoriomoviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.Toast
import java.util.ArrayList

class RecolectarBatallaActivity : AppCompatActivity() {

    var so : ArrayList<AppPorUsuario2>?=null
    var lista: RecyclerView? = null
    var layoutManager : RecyclerView.LayoutManager? = null
    var adaptador : AdaptadorVerAplicaciones? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recolectar_batalla)

        var listaVer = this
        so = DatabaseAPPorUsuario.getList()
        lista = findViewById(R.id.reciclerListaBR)
        layoutManager = LinearLayoutManager(this)
        adaptador = AdaptadorVerAplicaciones(so!!, object : ClickListener{
            override fun onClick(vista: View, posicion: Int) {
                val seleccion = findViewById<LinearLayout>(R.id.hijos)!!
                seleccion.setOnClickListener {
                    val popupMenu: PopupMenu = PopupMenu(listaVer, seleccion)
                    popupMenu.menuInflater.inflate(R.menu.batalla, popupMenu.menu)
                    popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.menu_seleccionar -> {
                                val intent = Intent(this@RecolectarBatallaActivity, BatallaMapsActivity::class.java)
                                startActivity(intent)
                                Toast.makeText(
                                    this@RecolectarBatallaActivity,
                                    "Su seleccion:" + item.title,
                                    Toast.LENGTH_SHORT
                                ).show()
                                true
                            }
                            else -> false

                        }
                    })
                    popupMenu.show()
                }
                Toast.makeText(applicationContext, so?.get(posicion)?.nombreApp, Toast.LENGTH_SHORT).show()
            }
        })
        lista?.layoutManager = layoutManager
        lista?.adapter= adaptador
        adaptador?.notifyDataSetChanged()
    }
}
