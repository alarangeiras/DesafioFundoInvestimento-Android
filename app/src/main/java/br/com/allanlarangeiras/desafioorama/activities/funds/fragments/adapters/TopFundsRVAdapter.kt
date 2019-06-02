package br.com.allanlarangeiras.desafioorama.activities.funds.fragments.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.allanlarangeiras.desafioorama.R
import br.com.allanlarangeiras.desafioorama.activities.fundDetail.FundDetailActivity
import br.com.allanlarangeiras.desafioorama.model.dto.Fund
import br.com.allanlarangeiras.desafioorama.services.FundsService

class TopFundsRVAdapter(
    val topFunds: List<Fund>): RecyclerView.Adapter<TopFundsRVAdapter.TopFundsViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): TopFundsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_top_funds_list, null)
        context = parent.context
        view.setLayoutParams(
            RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
        )

        return TopFundsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.topFunds.size
    }

    override fun onBindViewHolder(holder: TopFundsViewHolder, position: Int) {
        val fund = topFunds[position]
        holder.card.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, FundDetailActivity::class.java)
            intent.putExtra("fund", fund)
            context.startActivity(intent)
        })
        holder.simpleName.text = fund.simpleName
        holder.m12.text = FundsService.formatM12(fund.profitabilities.m12)
        holder.minimumApplication.text = FundsService.formatAmount(fund.operability.minimumInitialApplicationAmount)
    }


    class TopFundsViewHolder(
        private val parent:View): RecyclerView.ViewHolder(parent) {

        val simpleName: TextView
        val m12: TextView
        val minimumApplication: TextView
        val card: CardView

        init {
            card = parent.findViewById<CardView>(R.id.card)
            simpleName = parent.findViewById<TextView>(R.id.simpleName)
            m12 = parent.findViewById<TextView>(R.id.m12)
            minimumApplication = parent.findViewById<TextView>(R.id.minimumApplication)
        }

    }

}