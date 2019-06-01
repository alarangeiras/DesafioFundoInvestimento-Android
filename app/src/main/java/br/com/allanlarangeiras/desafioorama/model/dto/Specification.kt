package br.com.allanlarangeiras.desafioorama.model.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Specification(
    @SerializedName("fund_macro_strategy")
    val fundMacroStrategy: FundMacroStrategy,
    @SerializedName("fund_main_strategy")
    val fundMainStrategy: FundMainStrategy
): Serializable