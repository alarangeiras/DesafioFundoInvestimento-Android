package br.com.allanlarangeiras.desafioorama.activities.funds.fragments.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.allanlarangeiras.desafioorama.R
import br.com.allanlarangeiras.desafioorama.model.dto.Fund

class FundsListRVAdapter(
    private val fundsGrouped: Map<String, List<Fund>>): RecyclerView.Adapter<FundsListRVAdapter.FundsViewHolder>() {

    private var titles: List<String>
    private var content: List<List<Fund>>

    init {
        this.titles = fundsGrouped.keys.toList()
        this.content = fundsGrouped.values.toList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): FundsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_fund_group_list, parent, false)
        return FundsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.titles.size
    }

    override fun onBindViewHolder(holder: FundsViewHolder, position: Int) {
        val title = titles.get(position)
        val content = content.get(position)
        val subTitle = content[0].specification.fundMacroStrategy.name
        holder.title.text = title
        holder.subtitle.text = "($subTitle)"

    }


    class FundsViewHolder(
        parentView: View
    ): RecyclerView.ViewHolder(parentView) {

        val title: TextView
        val subtitle: TextView

        init{
            title = parentView.findViewById<TextView>(R.id.title)
            subtitle = parentView.findViewById<TextView>(R.id.subtitle)
        }

    }

}