package br.com.allanlarangeiras.desafioorama.activities.fundDetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import br.com.allanlarangeiras.desafioorama.R
import br.com.allanlarangeiras.desafioorama.model.dto.Fund
import kotlinx.android.synthetic.main.activity_fund_detail.*
import kotlinx.android.synthetic.main.include_app_bar.*

class FundDetailActivity: AppCompatActivity() {

    lateinit var fund: Fund

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fund_detail)

        toolbar.title = getText(R.string.funds)
        setSupportActionBar(toolbar)

        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        val bundle = intent.extras
        this.fund = bundle.getSerializable("fund") as Fund

        simpleName.text = fund.simpleName
        macroStrategy.text = fund.specification.fundMacroStrategy.name
        mainStrategy.text = fund.specification.fundMainStrategy.name

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return true
    }

}