package br.com.allanlarangeiras.desafioorama.model.actions

import br.com.allanlarangeiras.desafioorama.model.types.AmountRange

interface FilterByMinimumAmount {
    fun filterByAmount(amount: AmountRange)
}