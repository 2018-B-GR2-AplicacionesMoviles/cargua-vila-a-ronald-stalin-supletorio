package com.moviles.supletoriomoviles

import android.util.Log
import com.github.kittinunf.fuel.*
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.result.Result
import org.json.JSONArray


class DatabaseAPP() {
    companion object {
        var ip ="http://172.29.64.166:1337/Aplicacion"
        var aux = JSONArray()
        lateinit var resp : JSONArray

        fun insertarSO(ap: Aplicacion){
            ip.httpPost(listOf("id" to ap.idAP,
                "pesoGigas" to ap.pesoGigas,
                "versiones" to ap.versiones,
                "nombres" to ap.nombres,
                "urlDescarga" to ap.urlDescarga,
                "fechaLanzamiento" to ap.fechaLanzamiento,
                "costo" to ap.costo,
                "tipoAplicacion" to ap.tipoAplicacion,
                "soId" to ap.soId))
                .responseString{ request, _, result ->
                    Log.i("http-2",request.toString())
                }
        }
        fun editarSO(ap: Aplicacion){
            "${ip}/${ap.idAP}".httpPatch(listOf("pesoGigas" to ap.pesoGigas,
                "versiones" to ap.versiones,
                "nombres" to ap.nombres,
                "urlDescarga" to ap.urlDescarga,
                "fechaLanzamiento" to ap.fechaLanzamiento,
                "costo" to ap.costo,
                "tipoAplicacion" to ap.tipoAplicacion,
                "soId" to ap.soId))
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
        fun buscar(nombre: String): ArrayList<Aplicacion>{

            val aplicaciones: ArrayList<Aplicacion> = ArrayList()
            "${ip}/?nombres=${nombre}".httpGet().responseJson {
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

                val id = resp.getJSONObject(i).getInt("id")
                val pesoGigas = resp.getJSONObject(i).getDouble("pesoGigas")
                val versiones = resp.getJSONObject(i).getInt("versiones")
                val nombres = resp.getJSONObject(i).getString("nombres")
                val urlDescarga = resp.getJSONObject(i).getString("urlDescarga")
                val fechaLanzamiento =resp.getJSONObject(i).getString("fechaLanzamiento")
                val costo =resp.getJSONObject(i).getDouble("costo")
                var tipoAplicacion = resp.getJSONObject(i).getString("tipoAplicacion")
                val soId = resp.getJSONObject(i).getJSONObject("soId").getInt("id")
                val app = Aplicacion(id,pesoGigas,versiones,nombres,urlDescarga,fechaLanzamiento,costo,tipoAplicacion,soId)
                aplicaciones.add(app)
                Log.i("http-2", "DatosAP-2: ${app}")
            }
            Log.i("http-2", "DatosReturnAP: ${aplicaciones}")
            return aplicaciones
        }
        fun getListaIdSO(id: Int): ArrayList<Aplicacion>{

            val aplicaciones: ArrayList<Aplicacion> = ArrayList()
            "${ip}/?soId=${id}".httpGet().responseJson {
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

                val id = resp.getJSONObject(i).getInt("id")
                val pesoGigas = resp.getJSONObject(i).getDouble("pesoGigas")
                val versiones = resp.getJSONObject(i).getInt("versiones")
                val nombres = resp.getJSONObject(i).getString("nombres")
                val urlDescarga = resp.getJSONObject(i).getString("urlDescarga")
                val fechaLanzamiento =resp.getJSONObject(i).getString("fechaLanzamiento")
                val costo =resp.getJSONObject(i).getDouble("costo")
                var tipoAplicacion = resp.getJSONObject(i).getString("tipoAplicacion")
                val soId = resp.getJSONObject(i).getJSONObject("soId").getInt("id")
                val app = Aplicacion(id,pesoGigas,versiones,nombres,urlDescarga,fechaLanzamiento,costo,tipoAplicacion,soId)
                aplicaciones.add(app)
                Log.i("http-2", "DatosAP-2: ${app}")
            }
            Log.i("http-2", "DatosReturnAP: ${aplicaciones}")
            return aplicaciones
        }
        fun getList(): ArrayList<Aplicacion>{
            val aplicaciones: ArrayList<Aplicacion> = ArrayList()
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
               Log.i("http-3", "DatosAP-2: holaa${resp.getJSONObject(1).getJSONObject("soId").getInt("id")}")


            }
            for (i in 0 until aux.length()) {

                val id = resp.getJSONObject(i).getInt("id")
                val pesoGigas = resp.getJSONObject(i).getDouble("pesoGigas")
                val versiones = resp.getJSONObject(i).getInt("versiones")
                val nombres = resp.getJSONObject(i).getString("nombres")
                val urlDescarga = resp.getJSONObject(i).getString("urlDescarga")
                val fechaLanzamiento =resp.getJSONObject(i).getString("fechaLanzamiento")
                val costo =resp.getJSONObject(i).getDouble("costo")
                var tipoAplicacion = resp.getJSONObject(i).getString("tipoAplicacion")
                val soId = resp.getJSONObject(i).getJSONObject("soId").getInt("id")
                Log.i("http-3", "DatosAP-22: ${soId}")
                val app = Aplicacion(id,pesoGigas,versiones,nombres,urlDescarga,fechaLanzamiento,costo,tipoAplicacion,soId)
                aplicaciones.add(app)
                Log.i("http-3", "DatosAP-2: ${app}")
            }
            Log.i("http-3", "DatosReturnAP: ${aplicaciones}")
            return aplicaciones
        }
    }
}