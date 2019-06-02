package br.com.allanlarangeiras.desafioorama.activities.funds.dialogs

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.allanlarangeiras.desafioorama.R
import br.com.allanlarangeiras.desafioorama.services.FundsService

class FilterBottomSheetDialogFragment: BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contentView = inflater.inflate(R.layout.fragment_filter_bottom_sheet, container, false)

        Log.i(this::class.java.simpleName, FundsService.getMacroStrategies().toString())

        return contentView
    }

}