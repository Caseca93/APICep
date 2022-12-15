package com.sriyank.apicep.http

import com.google.gson.Gson

import com.sriyank.apicep.remote.CepEntityHttp
import okhttp3.*

class HttpHelperCep {

    private val url = "https://viacep.com.br/ws/"
    private val clientCep = OkHttpClient()

    fun getCep(cep: String): CepEntityHttp {

        val newUrl = "$url$cep/json/"

        val request = Request.Builder().url(newUrl).build()

        val response = clientCep.newCall(request).execute()

        val gson = Gson()

        return gson.fromJson(response.body()!!.string(), CepEntityHttp::class.java)
    }
}

//        val cep = binding.editCep.text.toString()
//        val newUrl = "$url$cep/json/"
//
//        request = Request.Builder().url(newUrl).build()
//
//        client.newCall(request).enqueue(object : okhttp3.Callback {
//            override fun onFailure(call: okhttp3.Call, e: IOException) {
//                e.printStackTrace()
//            }
//
//            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
//                if (response.isSuccessful){
//                    val gson = Gson()
//                    val body = gson.fromJson(response.body()?.string(), CepEntityHttp::class.java)
//                    runOnUiThread {
//                        // Stuff that updates the UI
//                        binding.textCep.text = body.toString()
//                    }
//
//                } else {
//                    runOnUiThread {
//                        // Stuff that updates the UI
//                        binding.textCep.text = "Informe um CEP"
//                    }
//                }
//            }
//
//        })

