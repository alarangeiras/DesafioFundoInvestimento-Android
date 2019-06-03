package br.com.allanlarangeiras.desafioorama.model.types

data class Filter(
    var macroStrategy: String = "Todos",
    var amount: AmountRange = AmountRange.AMOUNT_8)