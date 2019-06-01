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
import br.com.allanlarangeiras.desafioorama.services.FundsService

class TopFundsFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_top_funds, container, false)

        val topFunds = FundsService.getTopFunds()
        val linearLayoutManager = LinearLayoutManager(activity)
        view.findViewById<RecyclerView>(R.id.topFundsList).apply {
            layoutManager = linearLayoutManager
            adapter = TopFundsRVAdapter(topFunds)
        }

        return view

    }

}