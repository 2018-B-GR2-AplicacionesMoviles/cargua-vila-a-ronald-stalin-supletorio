package com.moviles.supletoriomoviles

import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost

class DatabaseAppEnBatalla(){
    companion object {
        var ip ="http://172.29.9.77:1337/AplicacionesEnBatalla"
        fun insertar(idInti:Int,idAppPor: Int, ganador: Boolean){
            ip.httpPost(listOf(
                "ganador" to ganador,
                "idAppPorUsuario" to idAppPor,
                "idInTiBatalla" to idInti
            )).responseString{ request, _, result ->
                Log.i("http-2",request.toString())
            }
        }
    }
}