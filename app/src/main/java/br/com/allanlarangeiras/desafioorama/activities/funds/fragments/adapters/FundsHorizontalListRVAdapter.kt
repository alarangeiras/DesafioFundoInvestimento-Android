package br.com.allanlarangeiras.desafioorama.activities.funds.fragments.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.allanlarangeiras.desafioorama.R
import br.com.allanlarangeiras.desafioorama.model.dto.Fund
import br.com.allanlarangeiras.desafioorama.utils.NumberUtils

class FundsHorizontalListRVAdapter(
    val fundsList: List<Fund>): RecyclerView.Adapter<FundsHorizontalListRVAdapter.FundsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): FundsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_fund_horizontal_list, parent, false)
        return FundsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.fundsList.size
    }

    override fun onBindViewHolder(holder: FundsViewHolder, positon: Int) {
        val fund = fundsList[positon]
        holder.simpleName.text = fund.simpleName
        val m12 = fund.profitabilities.m12
        var m12String = "-"
        if (m12 > 0) {
            m12String = "${NumberUtils.formatPercentage(m12 * 100)}% (12M)"
        }

        var applicationAmountString = fund.operability.minimumInitialApplicationAmount.toString()
        val minimumInitialApplicationAmount = fund.operability.minimumInitialApplicationAmount
        if (minimumInitialApplicationAmount > 0) {
            applicationAmountString = "R$ ${NumberUtils.formatMoney(minimumInitialApplicationAmount)}"
        }

        holder.m12.text = m12String
        holder.minimumApplication.text = applicationAmountString

    }

    class FundsViewHolder(
        parentView: View
    ): RecyclerView.ViewHolder(parentView) {

        val simpleName: TextView
        val m12: TextView
        val minimumApplication: TextView

        init{
            simpleName = parentView.findViewById<TextView>(R.id.simpleName)
            m12 = parentView.findViewById<TextView>(R.id.m12)
            minimumApplication = parentView.findViewById<TextView>(R.id.minimumApplication)
        }

    }

}