package com.sriyank.apicep

import com.google.gson.annotations.SerializedName

class CepEntity {

    @SerializedName("cep")
    var cep: String = ""

    @SerializedName("logradouro")
    var logradouro: String = ""

    @SerializedName("complemento")
    var complemento: String = ""

    @SerializedName("bairro")
    var bairro: String = ""

    @SerializedName("localidade")
    var localidade: String = ""

    @SerializedName("uf")
    var uf: String = ""

    @SerializedName("ibge")
    var ibge: String = ""

    @SerializedName("gia")
    var gia: String = ""

    @SerializedName("ddd")
    var ddd: String = ""

    @SerializedName("siafi")
    var siafi: String = ""

    override fun toString(): String {
        return "cep: $cep\n logradouro: $logradouro\n complemento: $complemento\n bairro: $bairro\n" +
                "localidade: $localidade\n uf: $uf\n ibge: $ibge\n gia: $gia\n ddd: $ddd\n siafi: $siafi"
    }
}