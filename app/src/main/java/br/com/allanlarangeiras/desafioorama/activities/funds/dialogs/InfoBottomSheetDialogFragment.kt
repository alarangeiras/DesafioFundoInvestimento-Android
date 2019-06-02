package br.com.allanlarangeiras.desafioorama.activities.funds.dialogs

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.allanlarangeiras.desafioorama.R

class InfoBottomSheetDialogFragment: BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contentView = inflater.inflate(R.layout.fragment_info_bottomsheet, container, false)

        val container = contentView.findViewById<View>(R.id.container)

        val macroTitleTextView = contentView.findViewById<TextView>(R.id.macroTitle)
        val macroDescriptionTextView = contentView.findViewById<TextView>(R.id.macroDescription)
        val mainTitleTextView = contentView.findViewById<TextView>(R.id.mainTitle)
        val mainDescriptionTextView = contentView.findViewById<TextView>(R.id.mainDescription)

        val bundle = arguments

        macroTitleTextView.text = bundle?.getString("macroTitle")
        macroDescriptionTextView.text = bundle?.getString("macroDescription")
        mainTitleTextView.text = bundle?.getString("mainTitle")
        mainDescriptionTextView.text = bundle?.getString("mainDescription")

        return contentView
    }

}