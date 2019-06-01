package br.com.allanlarangeiras.desafioorama.model.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Fund(
    @SerializedName("simple_name")
    val simpleName: String,
    val profitabilities: Profitabilities,
    val operability: Operability
): Serializable