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
import kotlinx.android.synthetic.main.activity_lista_app.*

class ListaAPPActivity : AppCompatActivity() {

    var so : ArrayList<Aplicacion>?=null
    var lista: RecyclerView? = null
    var layoutManager : RecyclerView.LayoutManager? = null
    var adaptador : AdaptadorListaAP? = null
    var idSO : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_app)



        val SOrecivido = intent.getParcelableExtra<SistemaOperativo>("SistemaOperativo")
        nombre_APP.text = "Nombre: "+ SOrecivido.nombre.toString()
        nombre_APP2.text = "Version API: "+ SOrecivido.versionApi.toString()
        nombre_APP3.text = "Fecha de Lanzamiento: "+ SOrecivido.fechaLanzamiento.toString()
        nombre_APP4.text = "Peso en Gigas: "+ SOrecivido.pesoGigas.toString()
        nombre_APP5.text = "Intalado: "+ SOrecivido.instalado.toString()
        idSO = SOrecivido.idSO.toInt()


        val listaSO = this
        so = DatabaseAPP.getListaIdSO(idSO)
        Log.i("bddd", "${so}")
        lista = findViewById(R.id.reciclerListaAP)
        layoutManager = LinearLayoutManager(this)
        adaptador = AdaptadorListaAP(so!!,object : ClickListener{
            override fun onClick(vista: View, posicion: Int) {
                val seleccion = findViewById<ConstraintLayout>(R.id.lista_app)
                seleccion.setOnClickListener {
                    val popupMenu: PopupMenu = PopupMenu(listaSO, seleccion)
                    popupMenu.menuInflater.inflate(R.menu.popup_menu_2,popupMenu.menu)
                    popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item->
                        when(item.itemId){
                            R.id.menu_editar->{
                                val intent = Intent(this@ListaAPPActivity,CrearAplicacionActivity::class.java)
                                intent.putExtra("Aplicacion",so?.get(posicion)as Aplicacion)
                                intent.putExtra("tipo","Edit")
                                startActivity(intent)
                                Toast.makeText(this@ListaAPPActivity,"Su seleccion:"+item.title, Toast.LENGTH_SHORT).show()
                                true
                            }
                            R.id.menu_eliminar->{
                                DatabaseAPP.eliminar(so?.get(posicion)?.idAP!!)
                                Toast.makeText(this@ListaAPPActivity,"Su seleccion:"+item.title, Toast.LENGTH_SHORT).show()
                                true
                            }
                            R.id.menu_compartir->{
                                val contenid = so?.get(posicion)?.nombres
                                val sendIntent : Intent =  Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT,contenid)
                                    type = "text/plain"
                                }
                                startActivity(sendIntent)
                                Toast.makeText(this@ListaAPPActivity,"Su seleccion:"+item.title, Toast.LENGTH_SHORT).show()
                                true
                            }
                            else -> false
                        }
                    })
                    popupMenu.show()
                }
                Toast.makeText(applicationContext, so?.get(posicion)?.nombres, Toast.LENGTH_SHORT).show()
            }
        })

        lista?.layoutManager = layoutManager
        lista?.adapter= adaptador

        adaptador?.notifyDataSetChanged()

        btn_nuevo_app.setOnClickListener {
            this.irCrearAplicacion(so?.get(0) as Aplicacion)
        }

    }
    fun irCrearAplicacion(ap : Aplicacion){
        val intent = Intent(this, CrearAplicacionActivity::class.java)
        intent.putExtra("Aplicacion",ap)
        intent.putExtra("tipo","Crear")
        startActivity(intent)
    }
}
