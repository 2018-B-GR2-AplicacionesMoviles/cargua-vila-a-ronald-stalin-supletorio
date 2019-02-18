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
        var ip ="http://172.29.64.166:1337/AplicacionPorUsuario"
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
        fun getList3(id:Int): ArrayList<AppPorUsuario>{
            val appPorUsuarios: ArrayList<AppPorUsuario> = ArrayList()
            "${ip}/?id=${id}".httpGet().responseJson {
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
                val idUsuario = resp.getJSONObject(i).getJSONObject("idUsuario").getInt("id")
                val idAplicacion = resp.getJSONObject(i).getJSONObject("idAplicacion").getInt("id")
                val experienciaAPP = resp.getJSONObject(i).getDouble("experienciaAPP")
                val numBatallas = resp.getJSONObject(i).getInt("numBatallas")
                val numRecolectas = resp.getJSONObject(i).getInt("numRecolectas")
                val appPor = AppPorUsuario(id,idAplicacion,idUsuario,experienciaAPP,numBatallas,numRecolectas)
                appPorUsuarios.add(appPor)
                Log.i("http-3", "DatosAP-3: ${appPor}")
            }
            Log.i("http-3", "DatosReturnAP-3: ${appPorUsuarios}")
            return appPorUsuarios
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
                val idUsuario = resp.getJSONObject(i).getJSONObject("idUsuario").getString("nombre")
                val idAplicacion = resp.getJSONObject(i).getJSONObject("idAplicacion").getString("nombres")
                val experienciaAPP = resp.getJSONObject(i).getDouble("experienciaAPP")
                val numBatallas = resp.getJSONObject(i).getInt("numBatallas")
                val numRecolectas = resp.getJSONObject(i).getInt("numRecolectas")
                val appPor = AppPorUsuario2(id,idAplicacion,idUsuario,experienciaAPP,numBatallas,numRecolectas)
                appPorUsuarios.add(appPor)
                Log.i("http-3", "DatosAP-3: ${appPor}")
            }
            Log.i("http-3", "DatosReturnAP-3: ${appPorUsuarios}")
            return appPorUsuarios
        }
        fun getList2(id: Int): Int{
            val appPorUsuarios: Int =0
            "${ip}/?id=${id}".httpGet().responseJson {
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
                val idAplicacion = resp.getJSONObject(i).getJSONObject("idAplicacion").getInt("id")
            }
            Log.i("http-3", "DatosReturnAP-3: ${appPorUsuarios}")
            return appPorUsuarios
        }
        fun getList4(id: Int): Int{
            val appPorUsuarios: Int =0
            "${ip}/?id=${id}".httpGet().responseJson {
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
                val idAplicacion = resp.getJSONObject(i).getJSONObject("idUsuario").getInt("id")
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