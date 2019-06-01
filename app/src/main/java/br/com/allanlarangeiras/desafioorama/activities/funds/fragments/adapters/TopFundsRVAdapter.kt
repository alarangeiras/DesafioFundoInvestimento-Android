package br.com.allanlarangeiras.desafioorama.activities.funds.fragments.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.allanlarangeiras.desafioorama.R
import br.com.allanlarangeiras.desafioorama.model.dto.Fund
import br.com.allanlarangeiras.desafioorama.services.FundsService

class TopFundsRVAdapter(
    val topFunds: List<Fund>): RecyclerView.Adapter<TopFundsRVAdapter.TopFundsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): TopFundsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_top_funds_list, null)
        return TopFundsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.topFunds.size
    }

    override fun onBindViewHolder(holder: TopFundsViewHolder, position: Int) {
        val fund = topFunds[position]
        holder.simpleName.text = fund.simpleName
        holder.m12.text = FundsService.formatM12(fund.profitabilities.m12)
        holder.minimumApplication.text = FundsService.formatAmount(fund.operability.minimumInitialApplicationAmount)
    }


    class TopFundsViewHolder(
        private val parent:View): RecyclerView.ViewHolder(parent) {

        var simpleName: TextView
        var m12: TextView
        var minimumApplication: TextView

        init {
            simpleName = parent.findViewById<TextView>(R.id.simpleName)
            m12 = parent.findViewById<TextView>(R.id.m12)
            minimumApplication = parent.findViewById<TextView>(R.id.minimumApplication)
        }

    }

}