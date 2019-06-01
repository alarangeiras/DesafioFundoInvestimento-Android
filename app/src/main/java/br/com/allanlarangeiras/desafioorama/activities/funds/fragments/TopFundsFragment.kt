package br.com.allanlarangeiras.desafioorama.activities.funds.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.allanlarangeiras.desafioorama.R
import br.com.allanlarangeiras.desafioorama.model.dto.Fund

class TopFundsFragment: Fragment() {

    private lateinit var funds: List<Fund>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_top_funds, container, false)

        val bundle = arguments
        this.funds = bundle?.getSerializable("funds") as List<Fund>
    }

}