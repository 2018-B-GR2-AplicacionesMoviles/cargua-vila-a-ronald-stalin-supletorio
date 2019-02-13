package com.moviles.supletoriomoviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.tapadoo.alerter.Alerter
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_crear_so.*
import kotlinx.android.synthetic.main.activity_crear_usuario.*
import kotlinx.android.synthetic.main.activity_menu_principal.*
import kotlin.math.log

class CrearSOActivity : AppCompatActivity() {

    lateinit var comboInstalado : Spinner;
    var opcion : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_so)

        btn_cancelar_so.setOnClickListener {
            this.irAMain()
        }
        comboInstalado = findViewById(R.id.instalado_so)
        val adapter : ArrayAdapter<CharSequence>
        adapter = ArrayAdapter.createFromResource(this, R.array.combo_intalado, android.R.layout.simple_spinner_item)

        comboInstalado.setAdapter(adapter);
        comboInstalado.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?,
                                        position: Int,
                                        id: Long) {
                opcion = parent!!.getItemAtPosition(position).toString()
                Log.i("opcion", opcion)
                Toast.makeText(parent!!.context,
                    "Seleccionado:"
                            +parent.getItemAtPosition(position).toString(),
                    Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.i("adaptador", "${parent}")
            }
        }
         btn_guardar_so.setOnClickListener {
             this.guardarDatos()
         }

    }
    fun irAMain(){
        val intent = Intent(this, MenuPrincipalActivity::class.java)
        startActivity(intent)
    }
    fun guardarDatos(){
        if (txt_nombre_so.text.toString().isEmpty()||
            txt_version_api_so.text.toString().isEmpty()||
            txt_fecha_lanzamiento_so.text.toString().isEmpty()||
            txt_peso_gigas_so.text.toString().isEmpty()){
            Alerter.create(this).setTitle("Campos Vacios")
                .setText("Completa la informacion de todos los campos")
                .setBackgroundColorRes(R.color.error_color_material_dark)
                .enableSwipeToDismiss()
                .show()
        }else{
            var nombreSO = txt_nombre_so.text.toString()
            var versionSO = txt_version_api_so.text.toString().toInt()
            var fechaLanzamientoSO = txt_fecha_lanzamiento_so.text.toString()
            var pesoGigasSO = txt_peso_gigas_so.text.toString().toDouble()
            var instaladoSO : Boolean
            if (opcion.equals("Si", true)){
                instaladoSO= true
            }else{
                instaladoSO = false
            }

            var siso = SistemaOperativo(0,nombreSO,versionSO,fechaLanzamientoSO,pesoGigasSO,instaladoSO)
            DatabaseSO.insertarSO(siso)

            Toasty.success(this, "Datos registrados", Toast.LENGTH_LONG, true).show()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }


}
