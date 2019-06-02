package br.com.allanlarangeiras.desafioorama.activities.funds.dialogs

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.allanlarangeiras.desafioorama.R

class InfoGeneralBottomSheetDialogFragmnet: BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contentView = inflater.inflate(R.layout.fragment_info_product_bottom_sheet, container, false)

        val titleTextView = contentView.findViewById<TextView>(R.id.infoTitle)
        val contentTextView = contentView.findViewById<TextView>(R.id.infoContent)

        titleTextView.text = getString(R.string.info_title)
        contentTextView.text = getString(R.string.info_description)

        return contentView
    }


}