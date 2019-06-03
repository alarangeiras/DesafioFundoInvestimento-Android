package br.com.allanlarangeiras.desafioorama.activities.funds.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.allanlarangeiras.desafioorama.R
import br.com.allanlarangeiras.desafioorama.activities.funds.fragments.adapters.TopFundsRVAdapter
import br.com.allanlarangeiras.desafioorama.model.actions.FilterByMinimumAmount
import br.com.allanlarangeiras.desafioorama.model.dto.Fund
import br.com.allanlarangeiras.desafioorama.model.types.AmountRange
import br.com.allanlarangeiras.desafioorama.model.types.Filter
import br.com.allanlarangeiras.desafioorama.services.FundsService

class TopFundsFragment: Fragment(), FilterByMinimumAmount {

    private lateinit var recyclerView: RecyclerView
    private lateinit var topFunds: MutableList<Fund>

    var filter = Filter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_top_funds, container, false)

        topFunds = FundsService.filterSorted(filter)
        val linearLayoutManager = LinearLayoutManager(activity)

        recyclerView = view.findViewById<RecyclerView>(R.id.topFundsList).apply {
            layoutManager = linearLayoutManager
            adapter = TopFundsRVAdapter(topFunds)
        }

        return view

    }

    override fun filterByAmount(amount: AmountRange) {
        filter.amount = amount
        val filteredByAmount = FundsService.filterSorted(filter)

        this.topFunds.clear()
        this.topFunds.addAll(filteredByAmount)

        this.recyclerView.adapter?.notifyDataSetChanged()
    }

}