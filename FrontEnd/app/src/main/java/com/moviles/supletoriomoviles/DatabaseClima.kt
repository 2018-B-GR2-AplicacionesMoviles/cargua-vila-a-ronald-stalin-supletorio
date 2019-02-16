package com.moviles.supletoriomoviles

import android.util.Log
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import org.json.JSONArray
import org.json.JSONObject

class DatabaseClima {
    companion object {
        val url = "https://api.openweathermap.org/data/2.5/weather?q=Quito&appid=32df5ad8dad7612bc67e71f67bdee321"
        var aux = JSONArray()
        lateinit var resp : JSONArray
        lateinit var clima : Clima
        fun obtenerClima(): Clima {

            var leng: JSONArray
            url.httpGet().responseJson {

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

                for (i in 0 until aux.length()) {
                    leng = resp.getJSONObject(i).getJSONArray("main")
                    for (j in 0 until leng.length()){
                        var temp = leng.getJSONObject(j).getDouble("temp")
                        var pressure =leng.getJSONObject(j).getDouble("pressure")
                        var humidity=leng.getJSONObject(j).getDouble("humidity")
                        var temp_min= leng.getJSONObject(j).getDouble("temp_min")
                        var temp_max= leng.getJSONObject(j).getDouble("temp_max")
                        var clim = Clima(temp,pressure,humidity,temp_min,temp_max)
                        clima = clim
                    }
                }
            }
            return clima
        }
    }
}