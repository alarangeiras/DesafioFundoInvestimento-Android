package br.com.allanlarangeiras.desafioorama.services

import br.com.allanlarangeiras.desafioorama.model.dto.Fund
import br.com.allanlarangeiras.desafioorama.model.dto.FundMacroStrategy
import br.com.allanlarangeiras.desafioorama.model.dto.Funds
import br.com.allanlarangeiras.desafioorama.proxies.FundDetailFullProxy
import br.com.allanlarangeiras.desafioorama.utils.NumberUtils
import br.com.allanlarangeiras.desafioorama.utils.RetrofitUtil
import io.reactivex.Observable

object FundsService {

    val fundDetailFullProxy: FundDetailFullProxy =
        RetrofitUtil.buildOramaProxy(FundDetailFullProxy::class.java)

    fun getFunds(): Observable<List<Fund>> {
        return fundDetailFullProxy.getPaginatedList()
    }

    fun getTopFunds():List<Fund> {
        val topFunds = Funds.all.sortedByDescending { fund: Fund -> fund.profitabilities.m12 }
        return topFunds.slice(0..4)
    }

    fun formatM12(m12: Float): String {
        var m12String = "-"
        if (m12 > 0) {
            m12String = "${NumberUtils.formatPercentage(m12 * 100)}% (12M)"
        }
        return m12String
    }

    fun formatAmount(amount: Double): String {
        var applicationAmountString = ""
        if (amount > 0) {
            applicationAmountString = "R$ ${NumberUtils.formatMoney(amount)}"
        }
        return applicationAmountString
    }

    fun getMacroStrategies(): MutableList<String> {
        return Funds.all.groupBy {fund -> fund.specification.fundMacroStrategy.name }
            .keys.toMutableList()
    }

    fun getGroupedFunds(funds: List<Fund> = Funds.all): MutableMap<String, List<Fund>> {
        var groupedFunds = funds.groupBy { fund -> fund.specification.fundMainStrategy.name }

        return groupedFunds as MutableMap<String, List<Fund>>
    }

    fun filterGrouped(macroStrategy: String): Map<out String, List<Fund>> {
        if (macroStrategy.equals("Todos", ignoreCase = true)) {
            return getGroupedFunds()
        }

        return getGroupedFunds(Funds.all.filter {
            it.specification.fundMacroStrategy.name.equals(macroStrategy, ignoreCase = true)
        })
    }


}