package com.moviles.supletoriomoviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import es.dmoral.toasty.Toasty

class ListaSOActivity : AppCompatActivity() {

    var so : ArrayList<SistemaOperativo>?=null
    var lista: RecyclerView? = null
    var layoutManager : RecyclerView.LayoutManager? = null
    var adaptador : AdaptadorListaSO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_so)

        val listaSO = this
        so = DatabaseSO.getList()
        Log.i("bddd", "${so}")
        lista = findViewById(R.id.reciclerListaSO)
        layoutManager = LinearLayoutManager(this)
        adaptador = AdaptadorListaSO(so!!,object : ClickListener{
            override fun onClick(vista: View, posicion: Int) {
                val seleccion = findViewById<ConstraintLayout>(R.id.lista_sos)
                seleccion.setOnClickListener {
                    val popupMenu: PopupMenu = PopupMenu(listaSO, seleccion)
                    popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)
                    popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {item->
                        when(item.itemId){
                            R.id.menu_editar->{
                                val intent = Intent(this@ListaSOActivity,EditarSOActivity::class.java)
                                startActivity(intent)
                                Toast.makeText(this@ListaSOActivity,"Su seleccion:"+item.title, Toast.LENGTH_SHORT).show()
                                true
                            }
                            R.id.menu_eliminar->{
                                Toast.makeText(this@ListaSOActivity,"Su seleccion:"+item.title, Toast.LENGTH_SHORT).show()
                                true
                            }
                            R.id.menu_lista_aplicaciones->{
                                Toast.makeText(this@ListaSOActivity,"Su seleccion:"+item.title, Toast.LENGTH_SHORT).show()
                                true
                            }
                            R.id.menu_compartir->{
                                Toast.makeText(this@ListaSOActivity,"Su seleccion:"+item.title, Toast.LENGTH_SHORT).show()
                                true
                            }
                            else -> false
                        }
                    })
                    popupMenu.show()
                }
                Toast.makeText(applicationContext, so?.get(posicion)?.nombre, Toast.LENGTH_SHORT).show()
            }
        })
        lista?.layoutManager = layoutManager
        lista?.adapter= adaptador
    }

    fun irAEditar(){
        val inten = Intent(this, EditarSOActivity::class.java)
        startActivity(inten)
    }
}
