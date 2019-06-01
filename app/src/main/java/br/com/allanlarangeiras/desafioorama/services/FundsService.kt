package br.com.allanlarangeiras.desafioorama.services

import br.com.allanlarangeiras.desafioorama.model.dto.Fund
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

}