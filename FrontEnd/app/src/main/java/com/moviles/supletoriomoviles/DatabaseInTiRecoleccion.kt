package com.moviles.supletoriomoviles

import android.util.Log
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import org.json.JSONArray

class DatabaseInTiRecoleccion {
    companion object {
        var ip ="http://172.29.64.166:1337/InteraccionesTipoBatalla"
        var aux = JSONArray()
        lateinit var resp : JSONArray

        fun insertarSO(so: InteraccionTipoRecoleccion){
            ip.httpPost(listOf("oroRecolectado" to so.oroRecolectado,
                "experienciaRecolectado" to so.experienciaRecolectado,
                "idAplicacion" to so.idApp,
                "idAplicacionPorUsuario" to so.idAppPorUsuario))
                .responseString{ request, _, result ->
                    Log.i("http-2",request.toString())
                }
        }
        fun editarSO(so: InteraccionTipoRecoleccion){
            "${ip}/${so.idIntiR}".httpPut(listOf("oroRecolectado" to so.oroRecolectado,
                "experienciaRecolectado" to so.experienciaRecolectado,
                "idAplicacion" to so.idApp,
                "idAplicacionPorUsuario" to so.idAppPorUsuario))
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
        fun getList(): ArrayList<InteraccionTipoRecoleccion>{
            val recoleccion: ArrayList<InteraccionTipoRecoleccion> = ArrayList()
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
                val oroRecolectado = resp.getJSONObject(i).getInt("oroRecolectado")
                val experienciaRecolectado = resp.getJSONObject(i).getInt("experienciaRecolectado")
                val idAplicacion = resp.getJSONObject(i).getJSONObject("idAplicacion").getInt("id")
                val idAplicacionPorUsuario =resp.getJSONObject(i).getJSONObject("idAplicacionPorUsuario").getInt("id")
                val siso = InteraccionTipoRecoleccion(id,idAplicacionPorUsuario,idAplicacion,oroRecolectado,experienciaRecolectado)
                recoleccion.add(siso)
                Log.i("http-2", "DatosSO: ${recoleccion}")
            }
            Log.i("http-2", "DatosReturnSO: ${recoleccion}")
            return recoleccion
        }
    }
}