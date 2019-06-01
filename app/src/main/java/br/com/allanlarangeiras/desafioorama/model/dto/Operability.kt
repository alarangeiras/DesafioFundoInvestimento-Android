package br.com.allanlarangeiras.desafioorama.model.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Operability(
    @SerializedName("minimum_initial_application_amount")
    val minimumInitialApplicationAmount: Int
): Serializable