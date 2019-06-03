package br.com.allanlarangeiras.desafioorama.activities.funds.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.allanlarangeiras.desafioorama.R
import br.com.allanlarangeiras.desafioorama.activities.funds.fragments.adapters.FundsListGroupRVAdapter
import br.com.allanlarangeiras.desafioorama.activities.funds.fragments.adapters.MacroStrategiesRVAdapter
import br.com.allanlarangeiras.desafioorama.model.actions.FilterByMinimumAmount
import br.com.allanlarangeiras.desafioorama.model.dto.Fund
import br.com.allanlarangeiras.desafioorama.model.types.AmountRange
import br.com.allanlarangeiras.desafioorama.model.types.Filter
import br.com.allanlarangeiras.desafioorama.services.FundsService

class FundsListFragment: Fragment(), FilterByMinimumAmount {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fundsGrouped: MutableMap<String, List<Fund>>

    var filter = Filter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_funds_list, container, false) as View

        fundsGrouped = FundsService.filterGrouped(filter)

        val linearLayoutManager = LinearLayoutManager(activity)

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = linearLayoutManager
            adapter = FundsListGroupRVAdapter(activity!!, fundsGrouped)
        }

        val macroStrategies = FundsService.getMacroStrategies()
        macroStrategies
            .add(0, "Todos")
        view.findViewById<RecyclerView>(R.id.macroStrategies).apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = MacroStrategiesRVAdapter(this@FundsListFragment, macroStrategies)
        }

        return view
    }

    fun filterByMacroStrategy(macroStrategy: String) {
        filter.macroStrategy = macroStrategy
        val filteredMacroStrategy = FundsService.filterGrouped(filter)

        this.fundsGrouped.clear()
        this.fundsGrouped.putAll(filteredMacroStrategy)

        this.recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun filterByAmount(amount: AmountRange) {
        filter.amount = amount
        val filteredByAmount = FundsService.filterGrouped(filter)

        this.fundsGrouped.clear()
        this.fundsGrouped.putAll(filteredByAmount)

        this.recyclerView.adapter?.notifyDataSetChanged()
    }

}