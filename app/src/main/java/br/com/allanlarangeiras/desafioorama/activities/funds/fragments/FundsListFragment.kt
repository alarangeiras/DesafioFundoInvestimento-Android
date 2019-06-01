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
import br.com.allanlarangeiras.desafioorama.model.dto.Fund
import br.com.allanlarangeiras.desafioorama.model.dto.Funds
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FundsListFragment: Fragment() {

    private lateinit var fundsGrouped: Map<String, List<Fund>>

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_funds_list, container, false)

        val funds = Funds.all

        this.fundsGrouped = funds.groupBy { fund -> fund.specification.fundMainStrategy.name }

        val linearLayoutManager = LinearLayoutManager(activity)

        this.recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = linearLayoutManager
            adapter = FundsListGroupRVAdapter(activity!!, fundsGrouped)
        }

        return view
    }

    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)


}