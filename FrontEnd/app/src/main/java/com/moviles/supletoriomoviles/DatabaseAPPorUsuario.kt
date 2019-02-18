package com.moviles.supletoriomoviles

import android.util.Log
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import org.json.JSONArray

class DatabaseAPPorUsuario {
    companion object {
        var ip ="http://192.168.3.103:1337/AplicacionPorUsuario"
        var aux = JSONArray()
        lateinit var resp : JSONArray

        fun insertarSO(ap: AppPorUsuario){
            ip.httpPost(listOf("id" to ap.idAppPorUsu,
                "experienciaAPP" to ap.experienciaApp,
                "idUsuario" to ap.idUsuario,
                "idAplicacion" to ap.idApp,
                "numBatallas" to ap.numBatallas,
                "numRecolectas" to ap.numRecolectas))
                .responseString{ request, res, result ->
                    Log.i("http-4",request.toString())
                    Log.i("http-4",res.toString())
                    Log.i("http-4",result.toString())
                }
        }
        fun editarSO(ap: AppPorUsuario){
            "${ip}/${ap.idAppPorUsu}".httpPut(listOf("id" to ap.idAppPorUsu,
                "idUsuario" to ap.idUsuario,
                "idAplicacion" to ap.idApp,
                "experienciaAPP" to ap.experienciaApp,
                "numBatallas" to ap.numBatallas,
                "numRecolectas" to ap.numRecolectas))
                .responseString{ request, _, result ->
                    Log.i("http-2",request.toString())
                }
        }
        fun eliminar(id: Int){
            "${ip}/${id}".httpDelete().responseString{
                    request, response, result ->
                Log.i("http-2",request.toString())
            }
        }

        fun getList(): ArrayList<AppPorUsuario2>{
            val appPorUsuarios: ArrayList<AppPorUsuario2> = ArrayList()
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
                        Log.i("http-2", "DatosAP: ${aux}")
                        Log.i("Tipo", "${aux::class.simpleName}")
                    }

                }
                resp = result.get().array()

            }
            for (i in 0 until aux.length()) {
                Log.i("http-3", "DatosReturnAP-3: a")

                val id = resp.getJSONObject(i).getInt("id")
                Log.i("http-3", "DatosReturnAP-3: b")

                val idUsuario = resp.getJSONObject(i).getJSONObject("idUsuario").getString("nombre")
                Log.i("http-3", "DatosReturnAP-3: ${idUsuario}")

                val idAplicacion = resp.getJSONObject(i).getJSONObject("idAplicacion").getString("nombres")
                Log.i("http-3", "DatosReturnAP-3: ${idAplicacion}")

                val experienciaAPP = resp.getJSONObject(i).getDouble("experienciaAPP")
                Log.i("http-3", "DatosReturnAP-3: e")

                val numBatallas = resp.getJSONObject(i).getInt("numBatallas")
                Log.i("http-3", "DatosReturnAP-3: f")

                val numRecolectas = resp.getJSONObject(i).getInt("numRecolectas")
                Log.i("http-3", "DatosReturnAP-3: g")

                val appPor = AppPorUsuario2(id,idAplicacion,idUsuario,experienciaAPP,numBatallas,numRecolectas)
                Log.i("http-3", "DatosReturnAP-3: h")

                appPorUsuarios.add(appPor)
                Log.i("http-3", "DatosAP-3: ${appPor}")
            }
            Log.i("http-3", "DatosReturnAP-3: ${appPorUsuarios}")
            return appPorUsuarios
        }
        fun getId(): Int{
            var id = 0
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

                        Log.i("http-2", "DatosAP: ${aux}")
                        Log.i("Tipo", "${aux::class.simpleName}")
                    }

                }
                resp = result.get().array()

            }
            for (i in 0 until aux.length()) {
                id = resp.getJSONObject(i).getInt("id")

            }
            return id
        }
    }
}