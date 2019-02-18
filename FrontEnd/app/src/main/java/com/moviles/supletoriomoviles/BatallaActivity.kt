package com.moviles.supletoriomoviles

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class BatallaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_batalla)

        var oro = intent.getIntExtra("ORO",0)
        var experiencia = intent.getIntExtra("EXPERIENCIA",0)

        var inTiBatalla = DatabaseInTiBatalla.getList()

        for (i in 0 until inTiBatalla.size){
            if(inTiBatalla[i]?.estado.equals("Espera")){

            }
        }


    }
}
