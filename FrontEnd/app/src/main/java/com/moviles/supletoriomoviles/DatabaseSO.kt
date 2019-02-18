package com.moviles.supletoriomoviles

import android.util.Log
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import org.json.JSONArray

class DatabaseSO(){
    companion object {
        var ip ="http://192.168.10.103:1337/Sistema_operativo"
        var aux = JSONArray()
        lateinit var resp : JSONArray

        fun insertarSO(so: SistemaOperativo){
            ip.httpPost(listOf("nombre" to so.nombre,
                "versionApi" to so.versionApi,
                "fechaLanzamiento" to so.fechaLanzamiento,
                "pesoGigas" to so.pesoGigas,
                "instalado" to so.instalado))
                .responseString{ request, _, result ->
                    Log.i("http-2",request.toString())
                }
        }
        fun editarSO(so: SistemaOperativo){
            "${ip}/${so.idSO}".httpPut(listOf("nombre" to so.nombre,
                "versionApi" to so.versionApi,
                "fechaLanzamiento" to so.fechaLanzamiento,
                "pesoGigas" to so.pesoGigas,
                "instalado" to so.instalado))
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
        fun getList(): ArrayList<SistemaOperativo>{
            val sistemasOperativos: ArrayList<SistemaOperativo> = ArrayList()
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
                val nombre = resp.getJSONObject(i).getString("nombre")
                val versionApi = resp.getJSONObject(i).getInt("versionApi")
                val fechaLanzamiento = resp.getJSONObject(i).getString("fechaLanzamiento")
                val pesoGigas =resp.getJSONObject(i).getDouble("pesoGigas")
                val instalado =resp.getJSONObject(i).getBoolean("instalado")
                val siso = SistemaOperativo(id,nombre,versionApi,fechaLanzamiento,pesoGigas,instalado)
                sistemasOperativos.add(siso)
                Log.i("http-2", "DatosSO: ${sistemasOperativos}")
            }
            Log.i("http-2", "DatosReturnSO: ${sistemasOperativos}")
            return sistemasOperativos
        }
    }
}