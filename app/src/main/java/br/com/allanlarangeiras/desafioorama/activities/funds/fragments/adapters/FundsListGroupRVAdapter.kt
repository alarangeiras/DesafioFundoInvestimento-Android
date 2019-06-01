package br.com.allanlarangeiras.desafioorama.activities.funds.fragments.adapters

import android.media.Image
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.allanlarangeiras.desafioorama.R
import br.com.allanlarangeiras.desafioorama.activities.funds.FundsActivity
import br.com.allanlarangeiras.desafioorama.model.dto.Fund

class FundsListGroupRVAdapter(
    private val activity: FragmentActivity,
    private val fundsGrouped: Map<String, List<Fund>>): RecyclerView.Adapter<FundsListGroupRVAdapter.FundsViewHolder>() {

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
        val lineContent = content.get(position)
        val subTitle = lineContent[0].specification.fundMacroStrategy.name
        holder.title.text = title
        holder.subtitle.text = "($subTitle)"

        holder.questionMark.setOnClickListener(View.OnClickListener {
            (activity as FundsActivity).showInfoBottomSheet(lineContent[0])
        })

        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        holder.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = FundsHorizontalListRVAdapter(lineContent)
        }

    }

    class FundsViewHolder(
        parentView: View
    ): RecyclerView.ViewHolder(parentView) {

        val questionMark: ImageView
        val title: TextView
        val subtitle: TextView
        val recyclerView: RecyclerView

        init{
            questionMark = parentView.findViewById<ImageView>(R.id.questionMark)
            title = parentView.findViewById<TextView>(R.id.title)
            subtitle = parentView.findViewById<TextView>(R.id.subtitle)
            recyclerView = parentView.findViewById<RecyclerView>(R.id.recyclerView)
        }

    }

}