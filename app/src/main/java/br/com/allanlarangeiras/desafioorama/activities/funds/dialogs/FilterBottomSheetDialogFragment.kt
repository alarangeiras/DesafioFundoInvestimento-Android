package br.com.allanlarangeiras.desafioorama.activities.funds.dialogs

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import br.com.allanlarangeiras.desafioorama.R
import br.com.allanlarangeiras.desafioorama.model.actions.FilterByMinimumAmount
import br.com.allanlarangeiras.desafioorama.model.types.AmountRange
import br.com.allanlarangeiras.desafioorama.services.FundsService

class FilterBottomSheetDialogFragment: BottomSheetDialogFragment() {

    var filterable: FilterByMinimumAmount? = null

    companion object {
        var selectedAmount: AmountRange = AmountRange.AMOUNT_8
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contentView = inflater.inflate(R.layout.fragment_filter_bottom_sheet, container, false)

        val minimumApplication = contentView.findViewById<SeekBar>(R.id.minimumApplication)
        val lblMinimumApplication = contentView.findViewById<TextView>(R.id.lblMinimumApplication)
        val filterButton = contentView.findViewById<Button>(R.id.filterButton)

        minimumApplication.progress = selectedAmount.index
        lblMinimumApplication.text = "Até ${FundsService.formatAmount(selectedAmount.amount)}"

        minimumApplication.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                selectedAmount = AmountRange.createByProgress(progress)
                lblMinimumApplication.text = "Até ${FundsService.formatAmount(selectedAmount.amount)}"

            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })

        filterButton.setOnClickListener {
            filterable?.filterByAmount(selectedAmount)
            this@FilterBottomSheetDialogFragment.dismiss()
        }

        return contentView
    }

}