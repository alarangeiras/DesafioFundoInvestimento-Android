package br.com.allanlarangeiras.desafioorama.proxies

import br.com.allanlarangeiras.desafioorama.model.dto.Fund
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FundDetailFullProxy {

        @GET("json/fund_detail_full.json")
    fun getPaginatedList(
        @Query("limit") limit:Int = 5,
        @Query("offset") offset:Int = 0): Observable<List<Fund>>

}