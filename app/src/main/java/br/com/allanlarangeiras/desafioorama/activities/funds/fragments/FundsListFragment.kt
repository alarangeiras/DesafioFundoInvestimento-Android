package br.com.allanlarangeiras.desafioorama.activities.funds.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.allanlarangeiras.desafioorama.R
import br.com.allanlarangeiras.desafioorama.activities.funds.fragments.adapters.FundsListRVAdapter
import br.com.allanlarangeiras.desafioorama.model.dto.Fund
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class FundsListFragment: Fragment() {

    private lateinit var fundsGrouped: Map<String, List<Fund>>

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_funds_list, container, false)

        val bundle = arguments
        val jsonString = bundle?.getString("funds")
        val funds = Gson().fromJson<List<Fund>>(jsonString!!)

        this.fundsGrouped = funds.groupBy { fund -> fund.specification.fundMainStrategy.name }

        val linearLayoutManager = LinearLayoutManager(activity)

        this.recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = linearLayoutManager
            adapter = FundsListRVAdapter(fundsGrouped)
        }

        for (item in this.fundsGrouped) {
            Log.i(this::class.java.simpleName, item.key)
        }

        return view
    }

    inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)


}