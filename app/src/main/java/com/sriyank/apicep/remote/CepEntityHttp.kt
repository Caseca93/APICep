package com.sriyank.apicep.remote

class CepEntityHttp {

    var cep: String = ""

    var logradouro: String = ""

    var complemento: String = ""

    var bairro: String = ""

    var localidade: String = ""

    var uf: String = ""

    var ibge: String = ""

    var gia: String = ""

    var ddd: String = ""

    var siafi: String = ""

    override fun toString(): String {
        return "cep: ${this.cep}\n logradouro: ${this.logradouro}\n complemento: ${this.complemento}" +
                "\n bairro: ${this.bairro}\n localidade: ${this.localidade}\n uf: ${this.uf}\n" +
                " ibge: ${this.ibge}\n gia: ${this.gia}\n ddd: ${this.ddd}\n siafi: ${this.siafi}"
    }
}