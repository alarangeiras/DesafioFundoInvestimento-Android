package br.com.allanlarangeiras.desafioorama.services

import br.com.allanlarangeiras.desafioorama.model.dto.Fund
import br.com.allanlarangeiras.desafioorama.proxies.FundDetailFullProxy
import br.com.allanlarangeiras.desafioorama.utils.RetrofitUtil
import io.reactivex.Observable

object FundsService {

    val fundDetailFullProxy: FundDetailFullProxy =
        RetrofitUtil.buildOramaProxy(FundDetailFullProxy::class.java)

    fun getPaginatedList(page:Int): Observable<List<Fund>> {
        return fundDetailFullProxy.getPaginatedList(offset = page)
    }

}