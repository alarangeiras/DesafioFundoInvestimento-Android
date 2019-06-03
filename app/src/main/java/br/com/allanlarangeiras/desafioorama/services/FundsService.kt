package br.com.allanlarangeiras.desafioorama.services

import br.com.allanlarangeiras.desafioorama.model.dto.Fund
import br.com.allanlarangeiras.desafioorama.model.dto.Funds
import br.com.allanlarangeiras.desafioorama.model.types.Filter
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

    fun filterGrouped(filter: Filter): MutableMap<String, List<Fund>> {
        return Funds.all.filter {
            getConditionResult(it, filter)
        }.groupBy { fund -> fund.specification.fundMainStrategy.name } as MutableMap<String, List<Fund>>
    }

    fun filterSorted(filter: Filter): MutableList<Fund> {
        return Funds.all.filter {
            getConditionResult(it, filter)
        }.sortedByDescending { fund: Fund -> fund.profitabilities.m12 }
            .slice(0..4) as MutableList<Fund>
    }

    fun getConditionResult(fund: Fund, filter: Filter): Boolean {
        if (filter.macroStrategy != "Todos") {
            return fund.operability.minimumInitialApplicationAmount <= filter.amount.amount &&
                    fund.specification.fundMacroStrategy.name.equals(filter.macroStrategy, ignoreCase = true)
        }

        return fund.operability.minimumInitialApplicationAmount <= filter.amount.amount
    }


}