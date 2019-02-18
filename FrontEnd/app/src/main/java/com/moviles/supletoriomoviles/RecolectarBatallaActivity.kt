package com.moviles.supletoriomoviles

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
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

        so = DatabaseAPPorUsuario.getList()

        lista = findViewById(R.id.reciclerListaRegistrar)
        layoutManager = LinearLayoutManager(this)
        adaptador = AdaptadorVerAplicaciones(so!!, object : ClickListener{
            override fun onClick(vista: View, posicion: Int) {
                val seleccion = findViewById<ConstraintLayout>(R.id.hijos)
                val popupMenu: PopupMenu = PopupMenu(this@RecolectarBatallaActivity, seleccion)
                popupMenu.menuInflater.inflate(R.menu.popup_menu_2,popupMenu.menu)
                popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item->
                    when(item.itemId){
                        R.id.menu_seleccionar->{
                            val intent = Intent(this@RecolectarBatallaActivity,BatallaMapsActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this@RecolectarBatallaActivity,"Su seleccion:"+item.title, Toast.LENGTH_SHORT).show()
                            true
                        }else ->false

                    }
                })
                Toast.makeText(applicationContext, so?.get(posicion)?.nombreApp, Toast.LENGTH_SHORT).show()
            }
        })
        lista?.layoutManager = layoutManager
        lista?.adapter= adaptador
        adaptador?.notifyDataSetChanged()
    }
}
