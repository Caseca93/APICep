package com.sriyank.apicep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.sriyank.apicep.databinding.ActivityMainBinding
import com.sriyank.apicep.remote.CepService
import com.sriyank.apicep.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var service: CepService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.buttonCep.setOnClickListener(this)

        service = RetrofitClient.createService(CepService::class.java)

    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.button_cep -> {
                val cep = binding.editCep.text.toString()
                val call: Call<CepEntity> = service.list(cep)

                call.enqueue(object : Callback<CepEntity> {
                    override fun onResponse(call: Call<CepEntity>, r: Response<CepEntity>) {
                        binding.textCep.text = r.body().toString()
                    }

                    override fun onFailure(call: Call<CepEntity>, t: Throwable) {
                        Toast.makeText(this@MainActivity,"Erro com a Internet", Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }
    }
}