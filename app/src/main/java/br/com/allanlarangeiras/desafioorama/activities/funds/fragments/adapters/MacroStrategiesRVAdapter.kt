package br.com.allanlarangeiras.desafioorama.activities.funds.fragments.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import br.com.allanlarangeiras.desafioorama.R
import br.com.allanlarangeiras.desafioorama.activities.funds.fragments.FundsListFragment

class MacroStrategiesRVAdapter(
    private val fragment: FundsListFragment,
    private val macroStrategies: List<String>): RecyclerView.Adapter<MacroStrategiesRVAdapter.MacroStrategyViewHolder>() {

    private val allViews: MutableList<MacroStrategyViewHolder> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MacroStrategyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_macro_strategy_filter, null)
        return MacroStrategyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return macroStrategies.size
    }

    override fun onBindViewHolder(holder: MacroStrategyViewHolder, position: Int) {
        val macroStrategy = macroStrategies[position]
        if (position == 0) {
            holder.btnMacroStrategy.setBackgroundResource(R.drawable.selected_button_background)
        }
        holder.btnMacroStrategy.text = macroStrategy
        holder.btnMacroStrategy.setOnClickListener(View.OnClickListener {
            allViews.forEach { view ->
                view.btnMacroStrategy.setBackgroundResource(R.drawable.button_background)
            }
            holder.btnMacroStrategy.setBackgroundResource(R.drawable.selected_button_background)
            fragment.filterByMacroStrategy(holder.btnMacroStrategy.text.toString())
        })

        allViews.add(holder)
    }

    class MacroStrategyViewHolder(val parent: View): RecyclerView.ViewHolder(parent) {
        val btnMacroStrategy: Button = parent.findViewById<Button>(R.id.btnMacroStrategy)
    }
}