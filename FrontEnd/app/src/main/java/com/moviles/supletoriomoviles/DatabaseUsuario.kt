package com.moviles.supletoriomoviles


import android.util.Log

import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import org.json.JSONArray



class DatabaseUsuario(){
    companion object {
        var ip ="http://192.168.3.103:1337/Usuario"
        var aux = JSONArray()
        lateinit var resp : JSONArray
        fun insertarUsuario(usuario: Usuario){
            ip.httpPost(listOf(
                "nombre" to usuario.nombre,
                "apellido" to usuario.apellido,
                "fechaNacimiento" to usuario.fechaNacimiento,
                "email" to usuario.email,
                "password" to usuario.password,
                "totalOro" to usuario.total_oro,
                "totalExperiencia" to usuario.total_experiencia))
                .responseString{request, _, result ->
                    Log.i("http-2",request.toString())
                }
        }
        fun getListId(id:Int):ArrayList<Usuario>{
            val usuarios: ArrayList<Usuario> = ArrayList()
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
                        Log.i("http-2", "Datos: ${aux}")
                        Log.i("Tipo", "${aux::class.simpleName}")
                    }

                }
                resp= result.get().array()

            }

            for (i in 0 until aux.length()) {
                val id = resp.getJSONObject(i).getInt("id")
                val nombre = resp.getJSONObject(i).getString("nombre")
                val apellido = resp.getJSONObject(i).getString("apellido")
                val fechaNacimiento = resp.getJSONObject(i).getString("fechaNacimiento")
                val email = resp.getJSONObject(i).getString("email")
                val password = resp.getJSONObject(i).getString("password")
                val totalOro = resp.getJSONObject(i).getInt("totalOro")
                val totalExperiencia = resp.getJSONObject(i).getInt("totalExperiencia")
                val usuario = Usuario(id,nombre,apellido,fechaNacimiento,email,password,totalOro,totalExperiencia)
                usuarios.add(usuario)
                Log.i("http-2", "Datos: ${usuarios}")
            }
            Log.i("http-2", "DatosReturn: ${usuarios}")
            return usuarios
        }
        fun getList():ArrayList<Usuario>{
            val usuarios: ArrayList<Usuario> = ArrayList()
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
                        Log.i("http-2", "Datos: ${aux}")
                        Log.i("Tipo", "${aux::class.simpleName}")
                    }

                }
                resp= result.get().array()

            }

            for (i in 0 until aux.length()) {
                val id = resp.getJSONObject(i).getInt("id")
                val nombre = resp.getJSONObject(i).getString("nombre")
                val apellido = resp.getJSONObject(i).getString("apellido")
                val fechaNacimiento = resp.getJSONObject(i).getString("fechaNacimiento")
                val email = resp.getJSONObject(i).getString("email")
                val password = resp.getJSONObject(i).getString("password")
                val totalOro = resp.getJSONObject(i).getInt("totalOro")
                val totalExperiencia = resp.getJSONObject(i).getInt("totalExperiencia")
                val usuario = Usuario(id,nombre,apellido,fechaNacimiento,email,password,totalOro,totalExperiencia)
                usuarios.add(usuario)
                Log.i("http-2", "Datos: ${usuarios}")
            }
            Log.i("http-2", "DatosReturn: ${usuarios}")
            return usuarios

        }
    }
}
