package com.sriyank.apicep.remote

import com.sriyank.apicep.CepEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CepService {

    @GET("{CEP}/json/")
    fun list(@Path("CEP")CEP: String): Call<CepEntity>
}