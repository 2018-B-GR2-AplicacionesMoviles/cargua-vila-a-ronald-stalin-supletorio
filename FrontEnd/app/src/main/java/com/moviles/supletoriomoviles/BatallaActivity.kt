package com.moviles.supletoriomoviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_batalla.*
import java.util.*

class BatallaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_batalla)

        var oro = intent.getIntExtra("ORO", 0)
        var experiencia = intent.getIntExtra("EXPERIENCIA", 0)
        var idApp1 = intent.getIntExtra("idApp", 0)
        var idPoUsu = intent.getIntExtra("idAppPorUsuario", 0)
        var idUsu = intent.getIntExtra("idUsuario", 0)


    }
}
