package com.moviles.supletoriomoviles

import android.util.Log
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import org.json.JSONArray

class DatabaseInTiBatalla(){
    companion object {
        var ip ="http://172.29.9.77:1337/InteraccionesTipoBatalla"
        var aux = JSONArray()
        lateinit var resp : JSONArray
        fun getClima():Double{
            var temp = 0.0
            "https://api.openweathermap.org/data/2.5/weather?id=3652462&appid=32df5ad8dad7612bc67e71f67bdee321"
                .httpGet().responseJson {
                        request, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val ex = result.getException()
                            Log.i("http-2", "Error: ${ex}")
                        }
                        is Result.Success -> {
                            val datos = result.get()
                            aux = datos.array()
                            temp = datos.array().getJSONObject(0).getJSONObject("main").getDouble("temp")
                            Log.i("http-9", "DatosSO: ${aux}")
                            Log.i("Tipo", "${temp}")
                        }

                    }
                    resp = result.get().array()
                    Log.i("http-9", "Clima: ${resp.getJSONObject(0).getJSONObject("main").getDouble("temp")}")

                }
                for (i in 0 until aux.length()) {
                    val id = resp.getJSONObject(i).getJSONObject("main").getDouble("temp")
                    temp= id
                }
            Log.i("http-9", "DatosSO: ${temp}")

            return temp
        }
        fun insertar(baralla: InteraccionTipoBatalla){
            ip.httpPost(listOf(
                "clima" to baralla.clima,
                "turnosJugados" to baralla.turnosJugados,
                "recompensaExp" to baralla.recompensaExperiencia,
                "recompensaOro" to baralla.recompensaOro,
                "idAplicacion" to baralla.idApp,
                "estado" to baralla.estado
            )).responseString{ request, _, result ->
                Log.i("http-9",request.toString())
                Log.i("http-9",result.toString())
            }
        }
        fun editar(baralla: InteraccionTipoBatalla){
            "${ip}/${baralla.idInti}".httpPut(listOf(
                "id" to baralla.idInti,
                "clima" to baralla.clima,
                "turnosJugados" to baralla.turnosJugados,
                "recompensaEx" to baralla.recompensaExperiencia,
                "recompensaOro" to baralla.recompensaOro,
                "idAplicacion" to baralla.idApp,
                "estado" to baralla.estado
            )).responseString{ request, _, result ->
                Log.i("http-6",request.toString())
                Log.i("http-6",result.toString())
            }
        }
        fun getList(): ArrayList<InteraccionTipoBatalla>{
            val recoleccion: ArrayList<InteraccionTipoBatalla> = ArrayList()
            ip.httpGet().responseJson {
                    request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-2", "Error: ${ex}")
                    }
                    is Result.Success -> {
                        val datos = result.get()
                        aux = datos.array()
                        Log.i("http-2", "DatosSO: ${aux}")
                        Log.i("Tipo", "${aux::class.simpleName}")
                    }

                }
                resp = result.get().array()

            }

            for (i in 0 until aux.length()) {
                val id = resp.getJSONObject(i).getInt("id")
                val clima = resp.getJSONObject(i).getDouble("clima")
                val turnosJugados = resp.getJSONObject(i).getInt("turnosJugados")
                val recompensaExperiencia = resp.getJSONObject(i).getInt("recompensaOro")
                val recompensaOro = resp.getJSONObject(i).getInt("recompensaExp")
                val idApp = resp.getJSONObject(i).getJSONObject("idAplicacion").getInt("id")
                val estado = resp.getJSONObject(i).getString("estado")
                val siso = InteraccionTipoBatalla(id,idApp,clima,turnosJugados,recompensaOro,recompensaExperiencia,estado)
                recoleccion.add(siso)
                Log.i("http-9", "DatosSO: ${recoleccion}")
            }
            Log.i("http-9", "DatosReturnSO: ${recoleccion}")
            return recoleccion
        }
        fun getId():Int{
            var idInTiRe = 0
            ip.httpGet().responseJson {
                    request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-2", "Error: ${ex}")
                    }
                    is Result.Success -> {
                        val datos = result.get()
                        aux = datos.array()
                        Log.i("http-2", "DatosSO: ${aux}")
                        Log.i("Tipo", "${aux::class.simpleName}")
                    }

                }
                resp = result.get().array()

            }

            for (i in 0 until aux.length()) {
                val id = resp.getJSONObject(i).getInt("id")
                idInTiRe= id
            }
            return idInTiRe
        }
        fun getId2(id:Int):Int{
            var idInTiRe = 0
            "${ip}/${id}".httpGet().responseJson {
                    request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        Log.i("http-2", "Error: ${ex}")
                    }
                    is Result.Success -> {
                        val datos = result.get()
                        aux = datos.array()
                        Log.i("http-2", "DatosSO: ${aux}")
                        Log.i("Tipo", "${aux::class.simpleName}")
                    }

                }
                resp = result.get().array()

            }

            for (i in 0 until aux.length()) {
                val id = resp.getJSONObject(i).getInt("turnosJugados")
                idInTiRe= id
            }
            return idInTiRe
        }

    }
}