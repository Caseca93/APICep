package com.sriyank.apicep

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.sriyank.apicep.databinding.ActivityMainBinding
import com.sriyank.apicep.remote.CepEntityHttp
import com.sriyank.apicep.remote.CepService
import com.sriyank.apicep.remote.RetrofitClient
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var service: CepService
    private lateinit var client: OkHttpClient
    private lateinit var url: String
    private lateinit var request: Request
    private var addPermission = arrayOf(Manifest.permission.INTERNET)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.buttonRetrofit.setOnClickListener(this)
        binding.buttonHttp.setOnClickListener(this)

        service = RetrofitClient.createService(CepService::class.java)

        client = OkHttpClient()

        url = "https://viacep.com.br/ws/"
    }

    private fun isInternetPermissionGranted(): Boolean{
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.INTERNET
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestAcessInternet(){
       requestPermissions(addPermission,1)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_retrofit -> {
                val cep = binding.editCep.text.toString()
                val call: Call<CepEntity> = service.list(cep)

                call.enqueue(object : Callback<CepEntity> {
                    override fun onResponse(call: Call<CepEntity>, r: Response<CepEntity>) {
                        binding.textCep.text = r.body().toString()
                    }

                    override fun onFailure(call: Call<CepEntity>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Erro com a Internet", Toast.LENGTH_SHORT)
                            .show()
                    }

                })
            }
            R.id.button_http -> {
                if(!isInternetPermissionGranted()){
                    requestAcessInternet();
                } else {
                    val cep = binding.editCep.text.toString()
                    val newUrl = "$url$cep/json/"

                    request = Request.Builder().url(newUrl).build()

                    client.newCall(request).enqueue(object : okhttp3.Callback {
                        override fun onFailure(call: okhttp3.Call, e: IOException) {
                            e.printStackTrace()
                        }

                        override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                            if (response.isSuccessful) {
                                val gson = Gson()
                                val body =
                                    gson.fromJson(response.body()?.string(), CepEntityHttp::class.java)

                                runOnUiThread {
                                    // Stuff that updates the UI
                                    if (body.cep != ""){
                                        binding.editCampCep.text = body.cep
                                        binding.editCampLogradura.text = body.logradouro
                                        binding.editCampComplemento.text = body.complemento
                                        binding.editCampBairro.text = body.bairro
                                        binding.editCampLocalidade.text = body.localidade
                                        binding.editCampUf.text = body.uf
                                        binding.editCampIbge.text = body.ibge
                                        binding.editCampGia.text = body.gia
                                        binding.editCampDdd.text = body.ddd
                                        binding.editCampSiafi.text = body.siafi
                                        binding.textCep.text = ""
                                    } else {
                                        binding.textCep.text = "Cep inexistente"
                                    }

                                }

                            } else {
                                runOnUiThread {
                                    // Stuff that updates the UI
                                    binding.textCep.text = "Informe um CEP"
                                }
                            }
                        }
                    })
                }
            }
        }
    }
}


