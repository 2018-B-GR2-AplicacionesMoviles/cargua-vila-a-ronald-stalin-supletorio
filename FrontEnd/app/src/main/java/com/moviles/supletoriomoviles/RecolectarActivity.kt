package com.moviles.supletoriomoviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_recolectar.*
import java.util.*

class RecolectarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recolectar)

        var oro = intent.getIntExtra("ORO",0)
        var experiencia = intent.getIntExtra("EXPERIENCIA",0)

        txt_oro.text = "Oro recolectado: "+ oro
        txt_experiencia.text = "Experiencia Recolectada: "+ experiencia
        btn_ir_menu.setOnClickListener {
            this.irAMenu()
        }
    }

    fun irAMenu(){
        val intent = Intent(this, MenuJuegoActivity::class.java)
        startActivity(intent)
    }
}
